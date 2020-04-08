package forum.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forum.model.Launch_activityBean;
import forum.service.ILaunch_activityService;

@WebServlet("/forum/UpdateItem.do")
public class UpdateDelArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=UTF-8");

		session = request.getSession(false);

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
		
		
		int article_Id =  Integer.parseInt(articleIdStr.trim());
		if(cmd.equalsIgnoreCase("DEL")) {
			service.DeleteArticle(article_Id);
		}
		
	}
}
