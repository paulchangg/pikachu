package orderProcess;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.MemberBean;
import shoppingCart.model.OrdersBean;
import shoppingCart.service.OrderService;
import shoppingCart.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderListServlet
 */
@WebServlet("/_05_orderProcess/orderList.do")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {      // 使用逾時
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		OrderService os = new OrderServiceImpl();
		List<OrdersBean> memberOrders = os.getMemberOrders(mb.getM_id());
		request.setAttribute("memberOrders", memberOrders);
		RequestDispatcher rd = request.getRequestDispatcher("/orderProcess/OrderList.jsp");
		rd.forward(request, response);
		return;
		
	}

}
