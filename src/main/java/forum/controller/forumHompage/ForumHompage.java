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

		for (int n = 0; n < 11; n++) {
			foumbean = list.get(n);

			String fname = foumbean.getFname();
			int f_id = foumbean.getF_id();

			session.setAttribute("f_id", String.valueOf(f_id));
			
			session.setAttribute("fname", fname);

			listFame.add(fname);

			request.setAttribute("fname", listFame);
		}

		if(listFame !=null ) {
		RequestDispatcher rd = request.getRequestDispatcher("/forum/ForumHompage.jsp");
		rd.forward(request, response);
		
		RequestDispatcher rd2 = request.getRequestDispatcher("/forum/ShowArticleMode.jsp");
		rd2.forward(request, response);
		
		}
		
	}

}
