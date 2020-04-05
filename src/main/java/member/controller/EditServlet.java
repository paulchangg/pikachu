package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.MemberBean;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;

@WebServlet("/member/edit.do")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		String nickname = "";
		String income = "";
		String education = "";
		String city = "";
		
		nickname = request.getParameter("nickname");
		income = request.getParameter("income");
		education = request.getParameter("education");
		city = request.getParameter("city");
		
		MemberService ms = new MemberServiceImpl();
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		mb.setNickname(nickname);
		mb.setIncome(income);
		mb.setEducation(education);
		mb.setCity(city);
		ms.updateMember(mb);
		
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/member/member_center.jsp"));
		
		
	}

}
