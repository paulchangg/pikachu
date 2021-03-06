package shoppingcart.service.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import init.HibernateUtils;
import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;
import shoppingcart.dao.OrderDao;
import shoppingcart.dao.OrderItemDao;
import shoppingcart.dao.impl.OrderDaoImpl;
import shoppingcart.dao.impl.OrderItemDaoImpl;
import shoppingcart.model.OrderItemBean;
import shoppingcart.model.OrdersBean;
import shoppingcart.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private SessionFactory factory;
	private OrderItemDao oidao;
	private OrderDao odao;
	private MemberDao mdao;

	public OrderServiceImpl() {
		factory = HibernateUtils.getSessionFactory();
		oidao = new OrderItemDaoImpl();
		odao = new OrderDaoImpl();
		mdao = new MemberDaoImpl();
	}

	@Override
	// 這是一個交易
	public void persistOrder(OrdersBean ob) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// 檢查每筆訂單明細所訂購之商品的庫存數量是否足夠
			checkStock(ob);
			// 儲存該筆訂單
			odao.insertOrder(ob);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());
			throw new RuntimeException(e);
		}

	}

	public void checkStock(OrdersBean ob) {
		Set<OrderItemBean> items = ob.getItems();
		for (OrderItemBean oib : items) {
			oidao.updateProductStock(oib);
		}
	}

//	@Override
//	// 本方法為過渡版本
//	public OrderBean getOrder(int orderNo) {
//		OrderBean bean = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			bean = odao.getOrder(orderNo);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null)
//				tx.rollback();
//			throw new RuntimeException(e);
//		}
//		return bean;
//	}

	@Override
// 本方法將由控制 Lazy Loading 的過濾器之doFilter()方法間接呼叫，所以不可以在此方法內執行與交易
// 有關的方法
	public OrdersBean getOrder(int orderNo) {
		OrdersBean bean = null;
		// Session session = factory.getCurrentSession();
		// Transaction tx = null;
		// try {
		// tx = session.beginTransaction();
		bean = odao.getOrder(orderNo);
		// tx.commit();
		// } catch (Exception e) {
		// if (tx != null) tx.rollback();
		// throw new RuntimeException(e);
		// }
		return bean;
	}

//	@Override
//	public List<OrderBean> getAllOrders() {
//		List<OrderBean> list = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			list = odao.getAllOrders();
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null)
//				tx.rollback();
//			throw new RuntimeException(e);
//		}
//		return list;
//	}
//
	@Override
	public List<OrdersBean> getMemberOrders(String memberId, int pageNo) {
		List<OrdersBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = odao.getMemberOrders(memberId,pageNo);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public long getRecordCounts(String m_id) {
		long recordCounts = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			recordCounts = odao.getRecordCounts(m_id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return recordCounts;
	}

	@Override
	public int getTotalPages(String m_id) {
		int totalPages = 1;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			totalPages = odao.getTotalPages(m_id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return totalPages;
	}
}
