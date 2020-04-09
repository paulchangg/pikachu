package member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import init.GlobalService;
import member.model.MemberBean;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;

@WebServlet("/member/changePassword.do")
public class ChangePwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsg = new HashMap<String, String>();
		
		HttpSession session = request.getSession(false);
		request.setAttribute("MsgMap", errorMsg);

		String password = "";
		String newpassword = "";
		String newpassword2 = "";

		password = request.getParameter("password");
		newpassword = request.getParameter("newpassword");
		newpassword2 = request.getParameter("newpassword2");
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		
		password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
		if(! password.equals(mb.getM_password())) {
			errorMsg.put("pwError","密碼錯誤");
		}
		
		if(! newpassword.equals(newpassword2)) {
			errorMsg.put("newPwError","新密碼不一致");
			
		}
		
		if(!errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("member_edit.jsp");
			rd.forward(request, response);
			return;
		}

		MemberService ms = new MemberServiceImpl();
		newpassword = GlobalService.getMD5Endocing(GlobalService.encryptString(newpassword));
		mb.setM_password(newpassword);
		ms.changePassword(mb);

		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/member/member_center.jsp"));

	}

}
