package forum.controller.responser;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
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
import forum.service.ILaunch_activityService;
import forum.service.IResponserService;
import forum.service.impl.Launch_activityServiceImpl;
import forum.service.impl.ResponserServiceImpl;
import member.model.MemberBean;
@MultipartConfig
@WebServlet("/forum/ResponserServlet")
public class ResponserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		
		request.setCharacterEncoding("UTF-8");
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 準備存放註冊成功之訊息的Map物件
		// session物件來存放共用資料。
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		HttpSession session = request.getSession();
		// 取會員資料
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		session.getAttribute("activity_info_page");
		
		
		
// --------------------------------------------------------------------------------------------------------------------------
		String article_IdStr = request.getParameter("article_IdStr");
		String res_contentStr = request.getParameter("res_contentStr");
		System.out.println("article_IdStr"+article_IdStr);
		System.out.println("res_contentStr"+res_contentStr);
		
		if (res_contentStr == null || res_contentStr.trim().length() <= 10) {
			errorMsg.put("res_contentError", "回復內容不能少於10個字");
		}
		
		if (!errorMsg.isEmpty()) {
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			response.sendRedirect( getServletContext().getContextPath()+ "/forum/activity_info_page.jsp");
			return;
		}
		
		Integer article_Id =Integer.parseInt(article_IdStr.trim());
		
		

		Timestamp ts = new Timestamp(System.currentTimeMillis());

		try {
			
			IResponserService responserservice = new ResponserServiceImpl();
			
			ILaunch_activityService labservice = new Launch_activityServiceImpl();
			
			Launch_activityBean launch_activityBean =labservice.getArticle_Id(article_Id); 
			
			
			ResponserBean responser = new ResponserBean(null, mb.getM_id(), ts, null, res_contentStr, launch_activityBean);
			
			responserservice.insertRescontent(responser);
			
			session.setAttribute("newresponser", responser);
			response.sendRedirect( getServletContext().getContextPath()+ "/forum/activity_info_page");
			
//			RequestDispatcher rd = request.getRequestDispatcher("/forum/activity_info_page");
//			rd.forward(request, response);
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
