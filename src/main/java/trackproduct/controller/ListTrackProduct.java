package trackproduct.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import listProduct.model.ProductBean;
import listProduct.service.ProductService;
import listProduct.service.impl.ProductServiceImpl;
import member.model.MemberBean;

@WebServlet("/trackproduct/listTrack")
public class ListTrackProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		// 只要舊的Session物件，如果找不到，不要建立新的Session物件，直接傳回 null
		HttpSession session = request.getSession(false); 
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		
		ProductService service = new ProductServiceImpl();
		
		Set<ProductBean> beans = service.listTrackProduct(mb) ;
		
		session.setAttribute("trackProduct", beans);
		
		response.sendRedirect(response.encodeRedirectURL ("../trackProduct/track.jsp"));
		return;
		
	}

}
