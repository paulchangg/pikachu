package member.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import init.GlobalService;
import member.model.MemberBean;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;

@WebServlet("/member/editPhoto.do")
public class EditPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		HttpSession session = request.getSession(false);
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		
		String imgname = "";
		String imgpath = "";
		Blob m_img = null;
		
		imgname = request.getParameter("m_img");
		imgpath = "/images/common/" + imgname + ".jpg";
		System.out.println(imgpath);

		try {
			m_img = GlobalService.fileToBlob(sc.getResourceAsStream(imgpath),sc.getResourceAsStream(imgpath).available());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		MemberService ms = new MemberServiceImpl();
		mb.setM_img(m_img);
		ms.updateM_img(mb);
		
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/member/member_edit.jsp"));
	}

}
