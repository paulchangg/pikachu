package forum.controller.Launch_activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@MultipartConfig(location = "D:\\程式片段", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/QueryLaunchMember")
public class QueryLaunchMember extends HttpServlet {
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
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {

			response.sendRedirect(getServletContext().getContextPath() + "/member/member_login.jsp");
			return;
		}

		String loginmember = mb.getM_id();

		System.out.println("loginmember" + loginmember);// james123

		request.setAttribute("loginmember", loginmember);
		
		
		

		ILaunch_activityService service = new Launch_activityServiceImpl();

		String DecoderFname = (String) session.getAttribute("DecoderFname");

		session.setAttribute("DecoderFname", DecoderFname);

		List<Launch_activityBean> launchMembers = service.getMemberArticles(mb.getM_id());
		session.setAttribute("launchMembers", launchMembers);

		IResponserService service2 = new ResponserServiceImpl();

		Integer article_Id = null;
		List<ResponserBean> responAll = new ArrayList<>();
		for (Launch_activityBean LaunchId : launchMembers) {

			article_Id = LaunchId.getArticle_Id();

			System.out.println("article_Id" + article_Id);

			responAll.addAll(service2.getAllContent(article_Id));

		}
		session.setAttribute("responAll", responAll);

		RequestDispatcher rd = request.getRequestDispatcher("/forum/QueryLaunchMember.jsp");
		rd.forward(request, response);
		return;

	}

}
