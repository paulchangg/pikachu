package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

@WebServlet("/member/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%!^'\"]).{8,})";
	private static final String EMAIL_PATTERN = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
	private static final String PHONE_PATTERN = "[0-9]{10}";
	
	private Pattern pattern = null;
	private Matcher matcher = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 準備存放註冊成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
		// 註冊成功後將用response.sendRedirect()導向新的畫面，所以需要
		// session物件來存放共用資料。
		
		HttpSession session = request.getSession();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		session.setAttribute("MsgOK", msgOK); // 顯示正常訊息
		
		String account = "";
		String password = "";
		String name = "";
		String email = "";
		String gender = "";
		String phone_num = "";
		String birthday = "";
		
		account = request.getParameter("account");
		password = request.getParameter("password");
		name = request.getParameter("name");
		email = request.getParameter("email");
		gender = request.getParameter("gender");
		phone_num = request.getParameter("phone_num");
		birthday = request.getParameter("birthday");
		
		if(account == null || account.trim().length() == 0) {
			errorMsg.put("errorIdEmpty", "帳號欄必須輸入");
		}
		if (password == null || password.trim().length() == 0) {
			errorMsg.put("errorPasswordEmpty", "密碼欄必須輸入");
		}
		if (name == null || name.trim().length() == 0) {
			errorMsg.put("errorNameEmpty", "姓名欄必須輸入");
		}
		if (email == null || email.trim().length() == 0) {
			errorMsg.put("errorEmailEmpty", "電子郵件欄必須輸入");
		}
		if (gender == null || gender.trim().length() == 0) {
			errorMsg.put("errorGenderEmpty", "性別欄必須輸入");
		}
		if (phone_num == null || phone_num.trim().length() == 0) {
			errorMsg.put("errorPhoneEmpty", "電話欄必須輸入");
		}
		if (birthday == null || birthday.trim().length() == 0) {
			errorMsg.put("errorBirthdayEmpty", "生日欄必須輸入");
		}
		
		if(errorMsg.isEmpty()) {
			pattern = Pattern.compile(PASSWORD_PATTERN);
			matcher = pattern.matcher(password);
			if ( !matcher.matches() ) {
				errorMsg.put("passwordError", "密碼至少含有一個大寫字母、小寫字母、數字與!@#$%!^'\"等四組資料組合而成，且長度不能小於八個字元");
			}
			
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(email);
			if ( !matcher.matches() ) {
				errorMsg.put("emailError", "信箱格式錯誤");
			}
			
			pattern = Pattern.compile(PHONE_PATTERN);
			matcher = pattern.matcher(phone_num);
			if ( !matcher.matches() ) {
				errorMsg.put("phoneError", "手機格式錯誤");
			}
		}
		
		if (!errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("member_register.jsp");
			rd.forward(request, response);
			return;
		}
		
		try {
			MemberService service = new MemberServiceImpl();
			if(service.idExists(account)) {
				errorMsg.put("errorIdDup", "此帳號已存在，請換新帳號");
			} else if (service.emailExists(email)){
				errorMsg.put("errorIdDup", "此信箱已被註冊，請換新信箱");
			}else {
				password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
				try {
					birthday = sdf.format(sdf1.parse(birthday));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				MemberBean mb = new MemberBean(account,password,name,phone_num,email,null,Date.valueOf(birthday),gender,null,null,null,null);
				
				int n = service.saveMember(mb);
				if(n == 1) {
					response.sendRedirect("../index.jsp");
					return;
				} else {
					errorMsg.put("errorIdDup", "新增此筆資料有誤(RegisterServlet)");
				}
				
			}
			
			if (!errorMsg.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
//				System.out.println("log:errorMsg not empty!!!!!");
				RequestDispatcher rd = request.getRequestDispatcher("member_register.jsp");
				rd.forward(request, response);
				return;
			}
			
		} catch(Exception e){
			e.printStackTrace();
			errorMsg.put("errorIdDup", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("member_register.jsp");
			rd.forward(request, response);
		}
		
		
		
	}

}
