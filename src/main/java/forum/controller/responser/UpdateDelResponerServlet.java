package forum.controller.responser;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
@MultipartConfig
@WebServlet("/forum/UpdateDelResponerServlet")
public class UpdateDelResponerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;

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

		// 沒有登入會員 前往首頁
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.html");
			return;
		}

		// 取出session物件內的Responser物件
		IResponserService service = (IResponserService) session.getAttribute("ResponserBean");
		if (service == null) {
			// 如果找不到回文(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			response.sendRedirect(getServletContext().getContextPath() + "/index.html");
			return;
		}

		String cmd = request.getParameter("cmd");
		String res_idStr = request.getParameter("res_id");
//		ILaunch_activityService lab = new Launch_activityServiceImpl();
		Launch_activityBean bean = new Launch_activityBean();

		int res_id = Integer.parseInt(res_idStr.trim());
		if (cmd.equalsIgnoreCase("DEL")) {
			service.DeleteArticle(res_id);

			RequestDispatcher rd = request.getRequestDispatcher("/forum/ShowArticleMode.jsp");
			rd.forward(request, response);
			return;

		} else if (cmd.equalsIgnoreCase("MOD")) {
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			String res_content = request.getParameter("res_content");
			IResponserService service2 = new ResponserServiceImpl();
			ResponserBean responser = new ResponserBean();
			responser.setRes_m_id(mb.getM_id());
			responser.setUpdateTime(ts);
			responser.setRes_content(res_content);

			service2.updateArticle(res_id, responser);

			RequestDispatcher rd = request.getRequestDispatcher("/forum/ShowArticleMode.jsp");
			rd.forward(request, response);
			return;
		}

	}

}
