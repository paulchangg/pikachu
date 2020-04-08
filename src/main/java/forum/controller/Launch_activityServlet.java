package forum.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import forum.model.FoumBean;
import forum.model.Launch_activityBean;
import forum.service.ILaunch_activityService;
import forum.service.impl.Launch_activityServiceImpl;
import init.GlobalService;
import member.model.MemberBean;

/*
 * 本程式在後端執行，它會
 * (1) 讀取使用者由前端網頁輸入資料，
 * (2) 進行必要的資料轉換，
 * (3) 檢查使用者輸入資料，
 * (4) 呼叫Business Service元件，進行Business Logic運算，
 * (5) 依照Business Logic運算結果來送回適當的畫面給前端的使用者。
 * 
 */
@WebServlet("/forum/Launch_activityServlet")
public class Launch_activityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=UTF-8");
		response.setContentType("image/jpeg");
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 準備存放註冊成功之訊息的Map物件
		// session物件來存放共用資料。
		HttpSession session = request.getSession();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息

		if (session == null) {
			// 請使用者登入
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "member_login.jsp"));
			return;
		}

		// 沒有登入會員 前往首頁
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.html");
			return;
		}
		// --------------------------------------------------------------
		String article_title = "";
		String article_content = "";
		String subject = "";
		String location = "";
		String starteTimeStr = "";
		String endTimeStr = "";
		String articleimagestr = "";
		long sizeInBytes = 0;
		InputStream is = null;

		// 取出HTTP multipart request內所有的parts
		Collection<Part> parts = request.getParts();
//		GlobalService.exploreParts(parts, request);
		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (parts != null) { // 如果這是一個上傳資料的表單
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);
				if (p.getContentType() == null) {
					if (fldName.equals("article_title")) {
						article_title = value;
					} else if (fldName.equals("article_content")) {
						article_content = value;
					} else if (fldName.equals("subject")) {
						subject = value;
					} else if (fldName.equals("location")) {
						location = value;
					} else if (fldName.equals("postTimeStr")) {
						starteTimeStr = value;
					} else if (fldName.equals("endTimeStr")) {
						endTimeStr = value;
					}
				} else {
					// 取出圖片檔的檔名
					articleimagestr = GlobalService.getFileName(p);
					// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
					articleimagestr = GlobalService.adjustFileName(articleimagestr,
							GlobalService.IMAGE_FILENAME_LENGTH);
					if (articleimagestr != null && articleimagestr.trim().length() > 0) {
						sizeInBytes = p.getSize();
						is = p.getInputStream();
					}
				}

			}
		}

		// 2. 檢核使用者的輸入資料，進行必要的資料轉換(Date)
		Date starte_Time = null;
		if (starteTimeStr == null && starteTimeStr.trim().length() > 0) {
		} else {
			try {
				starte_Time = java.sql.Date.valueOf(starteTimeStr);
			} catch (Exception e) {
				errorMsg.put("starte_TimeError", "開始欄格式錯誤，應該為yyyy/MM/dd/HH");
			}
		}
		Date endTime = null;
		if (endTimeStr == null && endTimeStr.trim().length() > 0) {
		} else {
			try {
				endTime = java.sql.Date.valueOf(endTimeStr);
			} catch (Exception e) {
				errorMsg.put("endTimeError", "結束時間格式錯誤，應該為yyyy/MM/dd/HH");
			}
		}

		// 3. 檢查使用者輸入資料(單純檢查)

		// 如果 article_title 欄位為空白，或少於10個字，放一個錯誤訊息到 errorMsgMap 之內
		if (article_title == null | article_title.trim().length() <= 11) {
			errorMsg.put("TitleError", "標題不能少於10個字");
		}
		// 如果有錯誤
		if (!errorMsg.isEmpty()) {
//	
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("Launch_activityServlet.jsp");
			rd.forward(request, response);
			return;
		}

		Timestamp ts = new Timestamp(System.currentTimeMillis());

		Blob blob = null;
		if (is != null) {
			try {
				blob = GlobalService.fileToBlob(is, sizeInBytes);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 4. 產生Launch_activityDao物件，以便進行Business Logic運算
		ILaunch_activityService service = new Launch_activityServiceImpl();
		MemberBean member = new MemberBean();

		FoumBean foumBean = new FoumBean();
//		Set<ResponserBean> items = new LinkedHashSet<>();

		// 將所有發文資料封裝到Launch_activityBean(類別的)物件

		try {
			Launch_activityBean article = new Launch_activityBean(null, member.getM_id(), article_title,
					article_content, blob, subject, location, ts, null, starte_Time, endTime, null, foumBean, null);

			service.insertArticle(article);
			request.setAttribute("Launch_activityBean", article);

			RequestDispatcher rd = request.getRequestDispatcher("Launch_activityServlet.jsp");
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
