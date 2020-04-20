package orderprocess;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoppingcart.model.OrdersBean;
import shoppingcart.service.OrderService;
import shoppingcart.service.impl.OrderServiceImpl;

// 依照會員編號與訂單編號來讀取某筆訂單的所有資料，這些資料將封裝為一個OrderBean物件

@WebServlet("/orderProcess/orderDetail.do")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String orderNo = request.getParameter("orderNo");
		int no = Integer.parseInt(orderNo.trim());
		
		OrderService orderService = new OrderServiceImpl();
		OrdersBean ob = orderService.getOrder(no);
		request.setAttribute("OrderBean", ob);   // 將OrderBean物件暫存到請求物件內
//		response.sendRedirect(response.encodeRedirectURL ("../orderProcess/orderItem.jsp"));
		RequestDispatcher rd = request.getRequestDispatcher("../orderProcess/orderItem.jsp");
		rd.forward(request, response);
		
		return;
	}

	
}