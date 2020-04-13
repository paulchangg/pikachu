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

import init.GlobalService;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;

@WebServlet("/forgetPW.do")
public class ForgetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Map<String, String> errorMsg = new HashMap<String, String>();
		Map<String, String> okMsg = new HashMap<String, String>();
		request.setAttribute("ErrorMsg", errorMsg);
		request.setAttribute("OkMsg", okMsg);
		String email = request.getParameter("email");
		
		MemberService ms = new MemberServiceImpl();
		
		if(email == null || email.trim().length() == 0) {
			errorMsg.put("EmptyError", "請輸入信箱");
		} else {
			if(ms.emailExists(email)) {
				String newPW = GlobalService.getRandomPassword();
				int r = ms.updatePassword(email, GlobalService.getMD5Endocing(GlobalService.encryptString(newPW)));
				if(r != 1) {
					//不太可能發生
					errorMsg.put("pswdUpdateError", "密碼更新錯誤");
				}
//			 	boolean b = ms.sendMail(email, newPW);
//			 	if(b == false) errorMsg.put("EmailError", "驗證信寄送失敗");
				ms.sendMail(email, newPW);
			} else {
				errorMsg.put("EmailError", "無此信箱,請重新輸入");
			}
		}
		
		if(errorMsg.isEmpty()) {
			okMsg.put("sendSuccessfully", "密碼驗證信已寄送");
			RequestDispatcher rd = request.getRequestDispatcher("member_login.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("member_login.jsp");
			rd.forward(request, response);
		}
		
	}
	
	

}
