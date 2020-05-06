package member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberBean;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;

@WebServlet("/member/RetrieveMemberImageServlet")
public class RetrieveMemberImageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
	OutputStream os = null;
	InputStream is = null;

	Blob blob = null;

	try {
		// 讀取瀏覽器傳送來的主鍵
		String id = request.getParameter("JoinPersonMid");
		// 讀取瀏覽器傳送來的type，以分辨要處理哪個表格
		MemberService service = new MemberServiceImpl();
		MemberBean bean = service.queryMember(id);
		
		if (bean != null) {
			blob = bean.getM_img();
			if (blob != null) {
				is = blob.getBinaryStream();
			}
			
		}
		if (is == null) {
		String filename ="membephoto_0.png";
			is = getServletContext().getResourceAsStream("/images/common/" + filename);

		}
		
		os = response.getOutputStream();	

		
		int len = 0;
		byte[] bytes = new byte[8192];
		while ((len = is.read(bytes)) != -1) {
			os.write(bytes, 0, len);
		}
	} catch(SQLException ex) {
		ex.printStackTrace();
	} finally{
		if (is != null) is.close();
		if (os != null) os.close();
		
	}
	
			
			
		}

}
