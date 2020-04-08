package forum.controller.Launch_activity;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forum.model.FoumBean;
import forum.model.Launch_activityBean;
import forum.model.ResponserBean;
import forum.service.ILaunch_activityService;
import forum.service.impl.Launch_activityServiceImpl;
import init.GlobalService;

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
		response.setContentType("text/json; charset=UTF-8");

		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();

		session = request.getSession(false);
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		if (session == null) {
			// 請使用者登入
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "member_login.jsp"));
			return;
		}

		// 取出session物件內的Launch_activity物件
		ILaunch_activityService service = (ILaunch_activityService) session.getAttribute("Launch_activityBean");
		if (service == null) {
			// 如果找不到文章(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			response.sendRedirect(getServletContext().getContextPath() + "/index.html");
			return;
		}
		// cmd可能是DEL或是MOD
		String cmd = request.getParameter("cmd");
		String articleIdStr = request.getParameter("article_Id");

		int article_Id = Integer.parseInt(articleIdStr.trim());
		if (cmd.equalsIgnoreCase("DEL")) {
			service.DeleteArticle(article_Id);// 刪除活動的某文章
			RequestDispatcher rd = request.getRequestDispatcher("/forum/ShowArticleMode.jsp");
			rd.forward(request, response);
			return;

		} else if (cmd.equalsIgnoreCase("MOD")) {

			String article_title = request.getParameter("article_title");
			String article_content = request.getParameter("article_content");
			String subject = request.getParameter("subject");
			String location = request.getParameter("location");
			String starteTimeStr = request.getParameter("starteTime");
			String endTimeStr = request.getParameter("endTime");
			String articleimagestr = "";

			long sizeInBytes = 0;
			InputStream is = null;

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

				if (article_title == null | article_title.trim().length() <= 11) {
					errorMsg.put("TitleError", "標題更新不能少於10個字");
				}
				if (article_content == null | article_content.trim().length() <= 101) {
					errorMsg.put("ContentError", "內容更新不能少於100個字");
				}
			}

			Timestamp ts = new Timestamp(System.currentTimeMillis());

			ILaunch_activityService service2 = new Launch_activityServiceImpl();
			Launch_activityBean article = new Launch_activityBean();
			article.setUpdateTime(ts);
			article.setArticle_content(article_content);
			article.setArticle_title(article_title);
			article.setSubject(subject);
			article.setStartTime(starte_Time);
			article.setEndTime(endTime);
			Set<ResponserBean> items = new LinkedHashSet<>();
			article.setItems(items);

			FoumBean foumBean = new FoumBean();
			article.setFoumBean(foumBean);

			Blob articleimage = null;

			if (articleimagestr != null && articleimagestr.trim().length() > 0) {

				try {
					articleimage = GlobalService.fileToBlob(articleimagestr);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			article.setArticleimage(articleimage);

			service2.updateArticle(article_Id, article);

			RequestDispatcher rd = request.getRequestDispatcher("/forum/ShowArticleMode.jsp");
			rd.forward(request, response);
			return;
		}

	}
}
