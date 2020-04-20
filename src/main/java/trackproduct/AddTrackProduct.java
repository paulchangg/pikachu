package trackproduct;

import java.io.IOException;
import java.util.Map;

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


@WebServlet("/trackproduct/DisplayPageProduct")
public class AddTrackProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
	}
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 只要舊的Session物件，如果找不到，不要建立新的Session物件，直接傳回 null
		HttpSession session = request.getSession(false); 
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
	
		String productIdStr 	= request.getParameter("productId");
		int productId          = Integer.parseInt(productIdStr.trim());
		


		Map<Integer, ProductBean> productMap = (Map<Integer, ProductBean>) session.getAttribute("products_DPP");
		ProductBean bean = productMap.get(productId);
		
		ProductService service = new ProductServiceImpl();
		
		service.saveTrackProduct(mb, productId);
		
		
		
		
		// 將訂單資料(價格，數量，折扣與BookBean)封裝到OrderItemBean物件內
		
		// 將OrderItem物件內加入ShoppingCart的物件內
		
		
//		RequestDispatcher rd = request.getRequestDispatcher("/listProduct/DisplayPageProducts?mode=show&productId="+bean.getP_id());
//		rd.forward(request, response);
		response.sendRedirect(response.encodeRedirectURL ("http://localhost:8080/pikachu/listProduct/DisplayPageProducts?mode=show&productId="+bean.getP_id()));
		return;
	}
}

