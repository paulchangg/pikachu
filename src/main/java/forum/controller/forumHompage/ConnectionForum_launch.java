package forum.controller.forumHompage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forum.model.FoumBean;
import member.model.MemberBean;

@WebServlet("/forum/ConnectionForum_launch")
public class ConnectionForum_launch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		this.doPost(request, response);
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
//
//		if (mb == null) {
//			response.sendRedirect(getServletContext().getContextPath() + "/member/member_login.jsp");
//			return;
//		}
		
		String	loginmember =	mb.getM_id();
		request.setAttribute("loginmember", loginmember); 


		
		System.out.println("loginmember"+loginmember);
		

		// 解碼查詢字串
		String DecoderFname = "";

		String query = request.getQueryString();

		if (query != null) {
			try {
				DecoderFname = URLDecoder.decode(query, "utf-8");// 将中文转码
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			session.setAttribute("Newsessionfname", DecoderFname);
			
			System.out.println("Newsessionfname="+DecoderFname);
			
			response.sendRedirect( getServletContext().getContextPath()+ "/forum/activity_page.jsp");
			
			
//			RequestDispatcher rd = request.getRequestDispatcher("/forum/activity_page.jsp");
//			rd.forward(request, response);
			return;
		}

	}

}
