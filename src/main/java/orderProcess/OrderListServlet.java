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
@WebServlet("/orderProcess/orderList.do")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageNo = 1;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {      // 使用逾時
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
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
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		String m_id = mb.getM_id();
		OrderService os = new OrderServiceImpl();
		List<OrdersBean> memberOrders = os.getMemberOrders(mb.getM_id(),pageNo);
		session.setAttribute("memberOrders", memberOrders);
		session.setAttribute("totalPages", os.getTotalPages(m_id));
		session.setAttribute("pageNo", pageNo);
//		RequestDispatcher rd = request.getRequestDispatcher("/orderProcess/orderQuery.jsp");
//		rd.forward(request, response);
		response.sendRedirect(response.encodeRedirectURL ("../orderProcess/orderQuery.jsp"));
		return;
		
	}

}
