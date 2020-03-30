package shoppingCart.dao;

import java.util.List;

import shoppingCart.model.OrdersBean;



public interface OrderDao {

	void insertOrder(OrdersBean ob);
	
	List<OrdersBean> getMemberOrders(String memberId);
	
	OrdersBean getOrder(int orderNo);

}