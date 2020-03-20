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

import listProduct.model.ProductBean;
import listProduct.service.ProductService;
import listProduct.service.impl.ProductServiceImpl;

//@WebServlet("/listProduct/DisplayPageProducts")
public class DisplayProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageNo = 1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
				HttpSession session = request.getSession(false);
				
				// BookService介面負責讀取資料庫內Book表格內某一頁的書籍資料，並能新增、修改、刪除
				// 書籍資料等。
				String pageNoStr = request.getParameter("pageNo");
				
				
				
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
				
				request.setAttribute("totalPages", service.getTotalPages());
				session.setAttribute("pageNo", String.valueOf(pageNo));
				session.setAttribute("products_DPP", productMap);
			
				RequestDispatcher rd = request.getRequestDispatcher("listBooks.jsp");
				rd.forward(request, response);
				return;
	}

}
