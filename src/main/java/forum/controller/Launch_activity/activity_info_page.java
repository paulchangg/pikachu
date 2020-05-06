package forum.controller.Launch_activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;

@WebServlet("/forum/activity_info_page")
public class activity_info_page extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String article_IdStr = request.getParameter("article_IdStr");

		System.out.println("article_IdStr" + article_IdStr);
		String mode = request.getParameter("mode");

		System.out.println("mode" + mode);
		ILaunch_activityService launch_activityService = new Launch_activityServiceImpl();

		int article_Id = 0;
		try {
			article_Id = Integer.parseInt(article_IdStr);
			System.out.println(article_Id + "article_Id ");
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}

		Launch_activityBean bean = launch_activityService.getArticle_Id(article_Id);

		if (mode == null) {

			ResponserBean b = (ResponserBean) session.getAttribute("newresponser");

			Launch_activityBean article_Id2 = b.getLaunch_activityBean();

			IResponserService service2 = new ResponserServiceImpl();

			List<ResponserBean> responAll = new ArrayList<>();

			responAll.addAll(service2.getAllContent(article_Id2.getArticle_Id()));

			session.setAttribute("responAll", responAll);

			System.out.println("responAll" + responAll);

			RequestDispatcher rd = request.getRequestDispatcher("/forum/activity_info_page.jsp");
			rd.forward(request, response);
			return;

		}

		if (mode.equalsIgnoreCase("add_activity")) {

//---------------------------參加人數顯示開始------------------------------------------------	

			

			MemberService memberservice = new MemberServiceImpl();

			List<MemberBean> JoinPersonName = memberservice.getActivityPerson(article_Id);// getActivityPerson重資料庫抓所有參加活動的人(依照文章的id)


			String querymemberid = null;

			List<String> joinmember = new ArrayList<String>();

			for (MemberBean b : JoinPersonName) {
				querymemberid = b.getM_id();// 取出JoinPersonNameList集合內的會員id

				joinmember.add(querymemberid);
			}
			String loginmember_id = ((MemberBean) session.getAttribute("LoginOK")).getM_id();
			
				if(!joinmember.contains(loginmember_id)) {
					Integer Popularity = bean.getPopularity(); 
					Popularity++;
					launch_activityService.addPopularity(article_Id, Popularity);
					
				}
			session.setAttribute("activity_info_page", bean);

//---------------------------參加人數顯示結束--------------------------	

//---------------------------右下角參加人的資訊開始 存入資料庫--------------------				
			Launch_activityBean member_launch_activity_article_Id = launch_activityService.getArticle_Id(article_Id);

			memberservice = new MemberServiceImpl();
			// 將資料寫入 member_launch_activity table(多對多的table)
			memberservice.addMyActivity(member_launch_activity_article_Id, loginmember_id);// 此動作會存入文章活動的id ， 參加的人員

//			response.sendRedirect(getServletContext().getContextPath() + "/forum/activity_info_page");
//
//			return;

//---------------------------右下角參加人的資訊結束 存入資料庫-------------------------------------------------------------------

//			List<String> JoinPerson = null;
//
//			JoinPerson = memberservice.getActivityPerson(article_Id);
//			System.out.println("JoinPerson" + JoinPerson);
//
//			session.setAttribute("JoinPerson", JoinPerson);

//---------------------------右下角參加人的資訊結束---------------------------------------------------------------------------			

		} else if (mode.equalsIgnoreCase("enterpage")) {

			session.setAttribute("activity_info_page", bean);

			

		} else if (mode.equalsIgnoreCase("leave_activity")) {


			String loginmember_id = ((MemberBean) session.getAttribute("LoginOK")).getM_id();
		

			
			MemberService memberservice = new MemberServiceImpl();
			List<MemberBean> JoinPersonName = memberservice.getActivityPerson(article_Id);
			

			String querymemberid = null;

			List<String> joinmember = new ArrayList<String>();

			for (MemberBean b : JoinPersonName) {
				querymemberid = b.getM_id();// 取出JoinPersonNameList集合內的會員id

				joinmember.add(querymemberid);
			}
			
			Integer Popularity = bean.getPopularity(); 
			if(joinmember.contains(loginmember_id)) {
				Popularity--;
				launch_activityService.addPopularity(article_Id, Popularity);
				
			}
			if (Popularity < 0) {
				Popularity = 0;
			}

			Launch_activityBean member_launch_activity_article_Id = launch_activityService.getArticle_Id(article_Id);
			
			launch_activityService.addPopularity(article_Id, Popularity);
			session.setAttribute("activity_info_page", bean);

			// 將資料寫入 member_launch_activity table(多對多的table)
			memberservice.leaveMyActivity(member_launch_activity_article_Id, loginmember_id);

//			response.sendRedirect(getServletContext().getContextPath() + "/forum/activity_info_page");
//
//			return;

		}

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		IResponserService service2 = new ResponserServiceImpl();

		List<ResponserBean> responAll = new ArrayList<>();

		responAll.addAll(service2.getAllContent(article_Id));

		session.setAttribute("responAll", responAll);

		List<MemberBean> JoinPersonName = null;
		MemberService memberservice = new MemberServiceImpl();
		JoinPersonName = memberservice.getActivityPerson(article_Id);
		session.setAttribute("JoinPersonName", JoinPersonName);
		
		


		String querymemberid = null;

		List<String> joinmember = new ArrayList<String>();

		for (MemberBean b : JoinPersonName) {
			querymemberid = b.getM_id();// 取出JoinPersonNameList集合內的會員id

			joinmember.add(querymemberid);
		}
		
		
		
		
		

		RequestDispatcher rd = request.getRequestDispatcher("/forum/activity_info_page.jsp");
		rd.forward(request, response);
		return;

	}

}
