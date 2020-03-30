package shoppingCart.controller;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.MemberBean;
import shoppingCart.model.OrderItemBean;
import shoppingCart.model.OrdersBean;
import shoppingCart.model.ShoppingCart;
import shoppingCart.service.OrderService;
import shoppingCart.service.impl.OrderServiceImpl;
// OrderConfirm.jsp 呼叫本程式。
@WebServlet("/_04_ShoppingCart/ProcessOrder.do")
public class ProcessOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String finalDecision = request.getParameter("finalDecision");		
		HttpSession session = request.getSession(false);
		if (session == null) {   // 使用逾時
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
			return;
		}
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
			return;
		}
		ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
		if (sc == null) {
			// 處理訂單時如果找不到購物車(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
			return;
		}
		// 如果使用者取消訂單
		if  (finalDecision.equals("CANCEL")){
			session.removeAttribute("ShoppingCart");
			response.sendRedirect(response.encodeRedirectURL (request.getContextPath()));
			return;  			// 一定要記得 return 
		}
		String memberId = mb.getM_id();   						// 取出會員代號
		double totalAmount = Math.round(sc.getSubtotal());  	// 計算訂單總金額 
		String shippingAddress = request.getParameter("ShippingAddress");  // 出貨地址
		Date today = new Date();   									// 新增訂單的時間
		// 新建訂單物件。OrderBean:封裝一筆訂單資料的容器，包含訂單主檔與訂單明細檔的資料。目前只存放訂單主檔的資料。
		
		// 取出存放在購物車內的商品，放入Map型態的變數cart，準備將其內的商品一個一個轉換為OrderItemBean，
		
		Map<Integer, OrderItemBean> content = sc.getContent();
		OrdersBean ob = new OrdersBean(null,memberId,today,totalAmount,null);		
		Set<OrderItemBean> items = new LinkedHashSet<>();
		Set<Integer> set = content.keySet();
		for(Integer i : set) {
			OrderItemBean oib = content.get(i);
			oib.setOrdersBean(ob);
			items.add(content.get(i));
		}
		
		// 執行到此，購物車內所有購買的商品已經全部轉換為為OrderItemBean物件，並放在Items內
		ob.setItems(items);
		try {
			OrderService orderService = new OrderServiceImpl();
			orderService.persistOrder(ob);
			session.setAttribute("Orders", ob);
			session.removeAttribute("ShoppingCart");
			response.sendRedirect(response.encodeRedirectURL ("../shoppingCart/showShoppingCart2.jsp"));
			return;
		} catch(RuntimeException ex){
			String message = ex.getMessage();
			String shortMsg = "" ;   
			shortMsg =  message.substring(message.indexOf(":") + 1);
			System.out.println(shortMsg);
			session.setAttribute("OrderErrorMessage", "處理訂單時發生異常: " + shortMsg  + "，請調正訂單內容" );
			response.sendRedirect(response.encodeRedirectURL ("../shoppingCart/showCartContent.jsp"));
			return;
		}
	}
}