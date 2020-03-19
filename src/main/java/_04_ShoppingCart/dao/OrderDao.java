package _04_ShoppingCart.dao;

import java.sql.Connection;
import java.util.List;

import _04_ShoppingCart.model.OrderBean;

public interface OrderDao {

	void insertOrder(OrderBean ob);

	void setConnection(Connection con);

	OrderBean getOrder(int orderNo);

	List<OrderBean> getAllOrders();

	List<OrderBean> getMemberOrders(String memberId);

}