package forum.controller.responser;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forum.model.Launch_activityBean;
import forum.model.ResponserBean;
import forum.service.IResponserService;
import forum.service.impl.ResponserServiceImpl;
import member.model.MemberBean;

@WebServlet("/forum/ResponserServlet")
public class ResponserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=UTF-8");
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 準備存放註冊成功之訊息的Map物件
		// session物件來存放共用資料。
		HttpSession session = request.getSession();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		if (session == null) {
			// 請使用者登入
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "member_login.jsp"));
			return;
		}
		// 沒有登入會員 前往首頁
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.html");
			return;
		}
		// --------------------------------------------------------------
		String res_contentStr = request.getParameter("res_content");

		if (res_contentStr == null | res_contentStr.trim().length() <= 31) {
			errorMsg.put("res_contentError", "回復內容不能少於30個字");
		}

		if (!errorMsg.isEmpty()) {
			//
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("/forum/ShowArticleMode.jsp");
			rd.forward(request, response);
			return;
		}

		Timestamp ts = new Timestamp(System.currentTimeMillis());

		try {
			IResponserService responserservice = new ResponserServiceImpl();
			Launch_activityBean launch_activityBean = new Launch_activityBean();
			ResponserBean responser = new ResponserBean(null, mb.getM_id(), ts, null, res_contentStr,
					launch_activityBean);
			responserservice.insertRescontent(responser);
			request.setAttribute("ResponserBean", responser);

			RequestDispatcher rd = request.getRequestDispatcher("/forum/ShowArticleMode.jsp");
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
