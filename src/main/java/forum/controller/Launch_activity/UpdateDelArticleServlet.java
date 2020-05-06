package forum.controller.Launch_activity;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import forum.model.Launch_activityBean;
import forum.service.ILaunch_activityService;
import forum.service.impl.Launch_activityServiceImpl;
import init.GlobalService;
import member.model.MemberBean;

@MultipartConfig
@WebServlet("/forum/UpdateItem.do")
public class UpdateDelArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
			
		
		
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();

		session = request.getSession();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息

		// 沒有登入會員 前往首頁
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.html");
			return;
		}

		// DEL或是MOD
		String mode = request.getParameter("mode");
		System.out.println("mode"+mode);
		String articleIdStr = request.getParameter("articleIdStr");
		System.out.println("articleIdStr" + articleIdStr);
		ILaunch_activityService service = new Launch_activityServiceImpl();

		Integer article_Id = Integer.parseInt(articleIdStr.trim());
		if (mode.equalsIgnoreCase("DEL")) {
			service.DeleteArticle(article_Id);// 刪除活動的某文章
			
			// 要用新頁面的

			response.sendRedirect( getServletContext().getContextPath()+ "/QueryLaunchMember");
		
			return;

		} else if (mode.equalsIgnoreCase("MOD")) {

			String article_title = request.getParameter("article_title");
			String article_content = request.getParameter("article_content");
			String subject = request.getParameter("subject");
			String location = request.getParameter("location");
			String starteTimeStr = request.getParameter("starteTime");
			String endTimeStr = request.getParameter("endTime");
			Part articleimageStr = request.getPart("articleimage");

			long sizeInBytes = 0;
			InputStream is = null;

			String fileName = GlobalService.getFileName(articleimageStr);
			System.out.println("fileName " + fileName);
			fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
			if (fileName != null && fileName.trim().length() > 0) {
				sizeInBytes = articleimageStr.getSize();
				is = articleimageStr.getInputStream();
			}

			Blob articleimage = null;
			if (is != null) {
				try {
					articleimage = GlobalService.fileToBlob(is, sizeInBytes);
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
				errorMsg.put("locationError", "活動地點不可空白");
			}

			if (starteTimeStr == null || starteTimeStr.trim().length() == 0) {
				errorMsg.put("starte_TimeError", "活動開始時間不可空白");
			}

			if (endTimeStr == null || endTimeStr.trim().length() == 0) {

				errorMsg.put("endTimeError", "活動結束時間不可空白");
			}
			if (!errorMsg.isEmpty()) {

				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				RequestDispatcher rd = request.getRequestDispatcher("/forum/QueryLaunchMember.jsp");
				rd.forward(request, response);
				return;
			}

// -----------------------------------------------------------------------------------------------------------------------------------------------------

			// 2. 檢核使用者的輸入資料，進行必要的資料轉換(Date)
			Date starteTime = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			try {
				starteTime = format.parse(starteTimeStr);
			} catch (Exception e) {

			}

			Date endTime = null;

			try {

				endTime = format.parse(endTimeStr);
			} catch (Exception e) {
			}

// --------------------------------------------------------------------------------------------------------------------------------------


			

			Timestamp updateTime = new Timestamp(System.currentTimeMillis());




			ILaunch_activityService service2 = new Launch_activityServiceImpl();
			Launch_activityBean article = new Launch_activityBean();

			article.setArticle_title(article_title);
			article.setArticle_content(article_content);

			article.setArticleimage(articleimage);
			article.setStartTime(starteTime);
			article.setEndTime(endTime);

			article.setUpdateTime(updateTime);

			article.setLocation(location);

			service2.updateArticle(article_Id, article);			
			
			
			
			
			response.sendRedirect( getServletContext().getContextPath()+ "/QueryLaunchMember");
			
//			RequestDispatcher rd = request.getRequestDispatcher("forum/QueryLaunchMember");
//			
//			rd.forward(request, response);

			return;
			
			
		}
		
	

	}
}
