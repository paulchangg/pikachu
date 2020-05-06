package forum.controller.responser;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forum.model.ResponserBean;
import forum.service.IResponserService;
import forum.service.impl.ResponserServiceImpl;
import member.model.MemberBean;

@MultipartConfig
@WebServlet("/forum/UpdateDelResponerServlet")
public class UpdateDelResponerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		session = request.getSession();

		// 沒有登入會員 前往首頁
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.html");
			return;
		}

		
		
		// DEL或是MOD
		String mode = request.getParameter("mode");
		System.out.println("mode" + mode);
		String res_idStr = request.getParameter("res_idStr");

		Integer res_id = Integer.parseInt(res_idStr.trim());
		IResponserService service = new ResponserServiceImpl();

		if (mode.equalsIgnoreCase("DEL")) {

			// 刪除活動的某文章
			service.DeleteArticle(res_id);
			// 要用新頁面的
			response.sendRedirect(getServletContext().getContextPath() + "/QueryLaunchALL");
			return;
			
		}else if (mode.equalsIgnoreCase("MOD")) {
			Timestamp updateTime = new Timestamp(System.currentTimeMillis());
			String res_content = request.getParameter("res_content");
			
			ResponserBean responser = new ResponserBean();
			
			responser.setUpdateTime(updateTime);
			responser.setRes_content(res_content);
			
			service.updateArticle(res_id, responser);
			response.sendRedirect(getServletContext().getContextPath() + "/QueryLaunchALL");
			
			return;
		
		}


	}

}
