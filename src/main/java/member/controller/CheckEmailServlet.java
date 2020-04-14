package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import init.GlobalService;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;

@WebServlet("/member/forgetPW.do")
public class CheckEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MemberService ms = new MemberServiceImpl();
		
		boolean b = false;
		if (email == null || email.trim().length() == 0) {
			out.println(false);
			
		} else {
			if (ms.emailExists(email)) {
				String newPW = GlobalService.getRandomPassword();
				ms.updatePassword(email, GlobalService.getMD5Endocing(GlobalService.encryptString(newPW)));
				
				ms.sendMail(email, newPW);
				out.println(true);
				out.close();
				
			} else {
				out.println(false);
				
			}
			
		}
		out.close();
	}

}
