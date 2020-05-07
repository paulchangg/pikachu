package forum.controller.forumHompage;

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

import forum.model.FoumBean;
import forum.model.Launch_activityBean;
import forum.service.IFoumService;
import forum.service.ILaunch_activityService;
import forum.service.impl.FoumServiceImpl;
import forum.service.impl.Launch_activityServiceImpl;
import member.model.MemberBean;

@WebServlet("/ForumHompage")
public class ForumHompage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int f_id = 1;
	String fname = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		//這邊是為了 點即所有活動按鈕時，啟動的
		if(session != null) {
		session.removeAttribute("sessionfname");
		session.removeAttribute("sessionf_id");
		session.removeAttribute("Newsessionfname");
		}
		
	
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
																			
			response.sendRedirect(getServletContext().getContextPath() + "/member/member_login.jsp");
			return;
		}
		
		
		
		
		String	loginmember =	mb.getM_id();
		System.out.println("loginmember"+loginmember);//james123
		session.setAttribute("loginmember", loginmember);    
		
		
		
		
//		
//		String f_idStr = request.getParameter("f_id");
//
//		if (f_idStr == null) {
//			f_id = 1;
//		} else {
//			try {
//				f_id = Integer.parseInt(f_idStr.trim());
//			} catch (NumberFormatException e) {
//				f_id = 1;
//			}
//		}

		IFoumService service = new FoumServiceImpl();

		List<FoumBean> list = service.getAllfname(f_id);

		FoumBean foumbean = null;

		List<String> listFame = new ArrayList<>();
		List<Integer> listF_id = new ArrayList<>();


		for (int n = 0; n < list.size(); n++) {

			foumbean = list.get(n);

			String fname = foumbean.getFname();
			
			
			Integer f_id = foumbean.getF_id();

			listFame.add(fname);
			listF_id.add(f_id);
			
			session.setAttribute("sessionfname", listFame);
			
			session.setAttribute("sessionf_id",listF_id );
			

		}
		
		
		ILaunch_activityService service2 = new Launch_activityServiceImpl();

		List<Launch_activityBean> launchAll = service2.getAllArticles();

		session.setAttribute("launchAll", launchAll);
	
		
		
//		System.out.println("f_id="+listF_id);//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
			
//		System.out.println(listFame); // [旅遊, 魯蛇卡, 加油回饋, 無限卡, 電影, 購物, 現金回饋, 宗教, 公益, 鈦金or御璽卡, 運動]

		RequestDispatcher rd = request.getRequestDispatcher("/forum/activity_page.jsp");
		rd.forward(request, response);

	}

}
