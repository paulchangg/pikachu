package forum.conrtoller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _04_ShoppingCart.model.OrderItemBean;
import _04_ShoppingCart.model.ShoppingCart;
import listProduct.model.ProductBean;

@WebServlet("/")
public class FoumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
	}
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false); 
		
		ShoppingCart cart = (ShoppingCart)session.getAttribute("ShoppingCart");
		if (cart == null) {
			cart = new ShoppingCart();
			session.setAttribute("ShoppingCart", cart);   
		}
		String productIdStr 	= request.getParameter("productId");
		int productId          = Integer.parseInt(productIdStr.trim());
		
		String qtyStr 		= request.getParameter("qty");
		Integer qty = 0 ; 

		Map<Integer, ProductBean> productMap = (Map<Integer, ProductBean>) session.getAttribute("products_DPP");
		ProductBean bean = productMap.get(productId);
		String pageNo 		= request.getParameter("pageNo");
		if (pageNo == null || pageNo.trim().length() == 0){
			pageNo = (String) session.getAttribute("pageNo") ;
			if (pageNo == null){
			   pageNo = "1";
			} 
		} 
		
		try{
			qty = Integer.parseInt(qtyStr.trim());
		} catch(NumberFormatException e){
			throw new ServletException(e); 
		}
		OrderItemBean oib = new  OrderItemBean(null,null,bean.getP_id(),bean.getPrice(),qty,cart.getSubtotal(),bean.getP_name());
		cart.addToCart(productId, oib);
		RequestDispatcher rd = request.getRequestDispatcher("/listProduct/DisplayPageProducts?pageNo=" + pageNo);
		rd.forward(request, response);
	}
}