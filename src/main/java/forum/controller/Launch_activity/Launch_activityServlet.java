package forum.controller.Launch_activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
//解碼查詢字串
		String cs = "";

		String query = request.getQueryString();

		if (query != null) {
			try {
				cs = URLDecoder.decode(query, "utf-8");// 将中文转码
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			session.setAttribute("sessionfname", cs);
			RequestDispatcher rd = request.getRequestDispatcher("/forum/ShowArticleMode.jsp");
			rd.forward(request, response);
			return;

		}

		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 準備存放註冊成功之訊息的Map物件
		// session物件來存放共用資料。

		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息

		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");

//		// --------------------------------------------------------------

		String article_title = request.getParameter("article_title");
		String article_content = request.getParameter("article_content");
		String subject = request.getParameter("subject");
		String location = request.getParameter("location");
		String starteTimeStr = request.getParameter("starteTime");
		String endTimeStr = request.getParameter("endTime");
		String articleimagestr = "";

//		long sizeInBytes = 0;
//		InputStream is = null;

		// 3. 檢查使用者輸入資料(單純檢查)

		// 如果 article_title 欄位為空白，或少於10個字，放一個錯誤訊息到 errorMsg 之內

		if (article_title == null || article_title.trim().length() <= 10) {
			errorMsg.put("TitleError", "標題不能少於10個字");
		}
		if (article_content == null || article_content.trim().length() <= 100) {
			errorMsg.put("ContentError", "內容不能少於100個字");
		}

		if (subject == null || subject.trim().length() == 0) {
			errorMsg.put("subjectError", "主題不可空白");
		}

		if (location == null || location.trim().length() == 0) {
			errorMsg.put("subjectError", "活動地點不可空白");
		}

		if (starteTimeStr == null || starteTimeStr.trim().length() == 0) {
			errorMsg.put("starte_TimeError", "活動開始時間不可空白");
		}

		if (endTimeStr != null && endTimeStr.trim().length() > 0) {

			errorMsg.put("endTimeError", "活動開始時間不可空白");

		}

		// 如果有錯誤
		if (!errorMsg.isEmpty()) {

			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("/forum/ShowArticleMode.jsp");
			rd.forward(request, response);
			return;
		}

		// 2. 檢核使用者的輸入資料，進行必要的資料轉換(Date)

		Date starte_Time = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/HH");

		try {
			starte_Time = format.parse(starteTimeStr);
		} catch (Exception e) {

		}

		Date endTime = null;

		try {

			endTime = format.parse(endTimeStr);
		} catch (Exception e) {
		}

		Timestamp ts = new Timestamp(System.currentTimeMillis());

		Blob articleimage = null;
		
			try {
				articleimage = GlobalService.fileToBlob(articleimagestr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

//		// 4. 產生Launch_activityDao物件，以便進行Business Logic運算

		ILaunch_activityService service = new Launch_activityServiceImpl();

		// 將所有發文資料封裝到Launch_activityBean(類別的)物件

		Integer f_id = (Integer) session.getAttribute("sessionf_id");
		
		FoumBean foumBean = new FoumBean();
		
		foumBean.setF_id(f_id);//版的代號

//		try {
			Launch_activityBean article = new Launch_activityBean(null, mb.getM_id(), article_title, article_content,
					articleimage, subject, location, ts, null, starte_Time, endTime, null, foumBean, null);

			service.insertArticle(article);
			request.setAttribute("Launch_activityBean", article);

			RequestDispatcher rd2 = request.getRequestDispatcher("/forum/InsertLaunchSuccess.jsp");
			rd2.forward(request, response);
			return;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
