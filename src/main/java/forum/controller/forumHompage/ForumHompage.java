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
import forum.service.IFoumService;
import forum.service.impl.FoumServiceImpl;
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
		
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
																			
			response.sendRedirect(getServletContext().getContextPath() + "/member/member_login.jsp");
			return;
		}
		
		
		
		String f_idStr = request.getParameter("f_id");

		if (f_idStr == null) {
			f_id = 1;
		} else {
			try {
				f_id = Integer.parseInt(f_idStr.trim());
			} catch (NumberFormatException e) {
				f_id = 1;
			}
		}

		IFoumService service = new FoumServiceImpl();

		List<FoumBean> list = service.getAllfname(f_id);

		FoumBean foumbean = null;

		List<String> listFame = new ArrayList<>();
		List<Integer> listF_id = new ArrayList<>();


		for (int n = 0; n < 11; n++) {

			foumbean = list.get(n);

			String fname = foumbean.getFname();
			
			
			Integer f_id = foumbean.getF_id();

			listFame.add(fname);
			listF_id.add(f_id);
			
			session.setAttribute("sessionfname", listFame);
			session.setAttribute("sessionf_id",listF_id );
			

		}
//		System.out.println("f_id="+listF_id);//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
			
	//	System.out.println(listFame);    //[旅遊, 魯蛇卡, 加油回饋, 無限卡, 電影, 購物, 現金回饋, 宗教, 公益, 鈦金or御璽卡, 運動]
		
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/forum/ForumHompage.jsp");
		rd.forward(request, response);

	}

}
