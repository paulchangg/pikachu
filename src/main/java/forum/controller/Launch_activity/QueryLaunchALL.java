package forum.controller.Launch_activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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

@WebServlet("/QueryLaunchALL")
public class QueryLaunchALL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		if (session == null) { // 使用逾時
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		
		if(session != null) {
			session.removeAttribute("responAll");
			session.removeAttribute("Newsessionfname");
			}
		
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		
		if (mb == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/member/member_login.jsp");
			return;
		}
		
		String	loginmember =	mb.getM_id();
		session.setAttribute("loginmember", loginmember);   
		
//		System.out.println("loginmember"+loginmember);//james123
		
		
		
		
		ILaunch_activityService service = new Launch_activityServiceImpl();

		List<Launch_activityBean> launchAll = service.getAllArticles();

		session.setAttribute("launchAll", launchAll);

	
//		for(Launch_activityBean  LaunchFame :launchAll) {
//			
//			
//		String	fame = LaunchFame.getFoumBean().getFname();
//				System.out.println("fame ="+fame);
//		}
		
		
//----------------------------------------------
		
		IResponserService service2 = new ResponserServiceImpl();

		Integer article_Id = null;
		List<ResponserBean> responAll = new ArrayList<>();
		for (Launch_activityBean LaunchId : launchAll) {

			article_Id = LaunchId.getArticle_Id();


			

			responAll.addAll(service2.getAllContent(article_Id));

		}
			session.setAttribute("responAll", responAll);

			response.sendRedirect( getServletContext().getContextPath()+ "/forum/activity_page.jsp");

			return;
	}

}