package listProduct.conrtoller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/listProduct/DisplayPageProducts")
public class DisplayProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageNo = 1;
	int id = 1;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
				
				// 先取出session物件
				HttpSession session = request.getSession(false);

				// 如果session物件不存在
				if (session == null) {
					// 請使用者登入
					response.sendRedirect(response.encodeRedirectURL(
							request.getContextPath() + "/member/member_login.jsp"));
					return;
				}
				
				MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
				// BookService介面負責讀取資料庫內Book表格內某一頁的書籍資料，並能新增、修改、刪除
				// 書籍資料等。
				String pageNoStr = request.getParameter("pageNo");
				String mode = request.getParameter("mode");
				String productId = request.getParameter("productId");
				
				if( productId == null ) {
					id = 1;
				}else {
					try {
						id = Integer.parseInt(productId.trim());
					} catch (NumberFormatException e) {
						id = 1;
					}
				}
				
				if(mode != null) {
					mode = "show";
				}
				
				if( pageNoStr == null ) {
					pageNo = 1;
				}else {
					try {
						pageNo = Integer.parseInt(pageNoStr.trim());
					} catch (NumberFormatException e) {
						pageNo = 1;
					}
				}
				
				ProductService service = new ProductServiceImpl(); 
				Map<Integer, ProductBean> productMap = service.getProduct(pageNo);
				ProductBean pb = service.getSelectBook(id);
				request.setAttribute("totalPages", service.getTotalPages());
				session.setAttribute("product_INFO", pb);
				session.setAttribute("pageNo", String.valueOf(pageNo));
				session.setAttribute("products_DPP", productMap);
				if(mode == "show") {
					RequestDispatcher rd = request.getRequestDispatcher("shopping_produce.jsp");
					rd.forward(request, response);
					return;
				}else {
					RequestDispatcher rd = request.getRequestDispatcher("shopping.jsp");
					rd.forward(request, response);
					return;					
				}
	}

}
