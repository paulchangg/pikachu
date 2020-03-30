package orderProcess;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingCart.model.OrdersBean;
import shoppingCart.service.OrderService;
import shoppingCart.service.impl.OrderServiceImpl;

// 依照會員編號與訂單編號來讀取某筆訂單的所有資料，這些資料將封裝為一個OrderBean物件

@WebServlet("/_05_orderProcess/orderDetail.do")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderNo = request.getParameter("orderNo");
		int no = Integer.parseInt(orderNo.trim());
		System.out.println("7777777777777777777777777777777777777777777777777777777777777");
		OrderService orderService = new OrderServiceImpl();
		OrdersBean ob = orderService.getOrder(no);
		request.setAttribute("OrderBean", ob);   // 將OrderBean物件暫存到請求物件內
		RequestDispatcher rd = request.getRequestDispatcher("../orderProcess/ShowOrderDetail.jsp");
		rd.forward(request, response);
		System.out.println("888888888888888888888888777777777777777777777777");
		return;
	}

	
}