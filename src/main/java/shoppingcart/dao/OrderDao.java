package shoppingcart.dao;

import java.util.List;

import shoppingcart.model.OrdersBean;



public interface OrderDao {

	void insertOrder(OrdersBean ob);
	
	List<OrdersBean> getMemberOrders(String memberId, int pageNo);
	
	OrdersBean getOrder(int orderNo);
	
	long getRecordCounts(String m_id);
	
	public int getTotalPages(String m_id);
}