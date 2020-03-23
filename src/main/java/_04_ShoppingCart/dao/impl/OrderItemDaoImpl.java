package _04_ShoppingCart.dao.impl;

import java.sql.Connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _04_ShoppingCart.dao.OrderItemDao;
import _04_ShoppingCart.model.OrderItemBean;
import _04_ShoppingCart.ude.ProductStockException;
import init.HibernateUtils;

public class OrderItemDaoImpl implements OrderItemDao {

	SessionFactory factory;
	public OrderItemDaoImpl(){
		factory = HibernateUtils.getSessionFactory();
	}
	
	@Override
	public double findItemAmount(OrderItemBean oib) {
		int n = 0;
		Integer stock = 0;
		Session session = factory.getCurrentSession();
		String hql0 = "SELECT stock FROM ProductBean WHERE p_id = :p_id";
		String hql1 = "UPDATE ProductBean SET stock = stock - :orderAmount WHERE p_id = :p_id";
		stock = (Integer) session.createQuery(hql0)
								 .setParameter("p_id", oib.getP_id())
								 .uniqueResult();
		if (stock == null) {
			stock = 0;
		}
		int stockLeft = stock - oib.getQty();
		if (stockLeft < 0) {
			throw new ProductStockException(
					"庫存數量不足: p_id: " + oib.getP_id() + ", 在庫量: " + stock + ", 訂購量: " 
					+ oib.getQty());
		}

		n = session.createQuery(hql1)
					.setParameter("p_id", oib.getP_id())
					.setParameter("orderAmount", oib.getQty())
					.executeUpdate();
		return n;
	}

	@Override
	public int updateProductStock(OrderItemBean ob) {
		
		return 0;
	}

	@Override
	public void setConnection(Connection conn) {
		
		
	}

}
