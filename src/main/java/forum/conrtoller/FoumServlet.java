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
// ?��使用?��?��?��?��?�入購物車�?��?��?��?�覽?��??��?�出請�?�到?��程�??
//@WebServlet("/listProduct/BuyProduct.do")
public class FoumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
	}
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ?��要�?��?�Session?��件�?��?��?�找不到，�?��?�建立新??�Session?��件�?�直?��?��??? null
		HttpSession session = request.getSession(false); 
		
		// ??�出存放?��session?��件內??�ShoppingCart?���?
		ShoppingCart cart = (ShoppingCart)session.getAttribute("ShoppingCart");
		// 如�?�找不到ShoppingCart?���?
		if (cart == null) {
			// 就新建ShoppingCart?���?
			cart = new ShoppingCart();
			// 並�?�此?��建ShoppingCart??�物件放?��session?��件內，�?�為它�?�屬?�物�?
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
			// ?��?��?��?��?��?��?��?��??
			qty = Integer.parseInt(qtyStr.trim());
		} catch(NumberFormatException e){
			throw new ServletException(e); 
		}
		// 將�?�單資�??(?��?��，數??��?��?�扣??�BookBean)封�?�到OrderItemBean?��件內
		OrderItemBean oib = new  OrderItemBean(null,null,bean.getP_id(),bean.getPrice(),qty,cart.getSubtotal(),bean.getP_name());
		// 將OrderItem?��件內??�入ShoppingCart??�物件內
		cart.addToCart(productId, oib);
		RequestDispatcher rd = request.getRequestDispatcher("/listProduct/DisplayPageProducts?pageNo=" + pageNo);
		rd.forward(request, response);
	}
}