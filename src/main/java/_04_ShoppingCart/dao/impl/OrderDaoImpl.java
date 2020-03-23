package _04_ShoppingCart.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _04_ShoppingCart.dao.OrderDao;
import _04_ShoppingCart.model.OrdersBean;
import init.HibernateUtils;

public class OrderDaoImpl implements OrderDao{

	private String memberId = null;
	SessionFactory factory;
	int orderNo = 0;

	public OrderDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	@Override
	public void insertOrder(OrdersBean ob) {
		Session session = factory.getCurrentSession();
        session.save(ob);	
	}

}
