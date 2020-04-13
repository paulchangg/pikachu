package forum.controller.forumHompage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forum.model.FoumBean;

@WebServlet("/forum/ConnectionForum_launch")
public class ConnectionForum_launch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		HttpSession session = request.getSession();
	 
		FoumBean fob = (FoumBean) session.getAttribute("fname");
		if (fob == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/forum/ForumHompage.jsp");

		} else {
			response.sendRedirect(getServletContext().getContextPath() + "/forum/ShowArticleMode.jsp");

		}

	}

}
