package shoppingCart.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import init.HibernateUtils;
import shoppingCart.dao.OrderDao;
import shoppingCart.model.OrdersBean;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdersBean> getMemberOrders(String memberId) {
		List<OrdersBean> list = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM OrdersBean ob WHERE ob.m_id = :mid";
		list = session.createQuery(hql)
						.setParameter("mid", memberId)
						.getResultList();
		return list;
	}

	@Override
	@Transactional 
	public OrdersBean getOrder(int ordid) {
		OrdersBean ob = null;
        Session session = factory.getCurrentSession();
        ob = session.get(OrdersBean.class, ordid);
        Hibernate.initialize(ob);
        return ob;
	}

}
