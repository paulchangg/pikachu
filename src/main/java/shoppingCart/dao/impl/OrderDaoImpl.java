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

	private int recordsPerPage = init.GlobalService.RECORDS_PER_PAGE - 2;
	private String memberId = null;
	private int totalPages;
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
	public List<OrdersBean> getMemberOrders(String memberId, int pageNo) {
		List<OrdersBean> list = null;
		Session session = factory.getCurrentSession();
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		String hql = "FROM OrdersBean ob WHERE ob.m_id = :mid";
		list = session.createQuery(hql)
						.setParameter("mid", memberId)
						.setFirstResult(startRecordNo)
	                    .setMaxResults(recordsPerPage)
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
	
	@Override
	public long getRecordCounts(String m_id) {
		long count = 0; // 必須使用 long 型態
        String hql = "SELECT count(*) FROM OrdersBean o WHERE o.m_id = :m_id";
        Session session = factory.getCurrentSession();
        List<Long> list = session.createQuery(hql)
        						.setParameter("m_id", m_id)
        						.getResultList();
        if (list.size() > 0) {
            count = list.get(0);
        }
        return count;
	}
	
	@Override
	public int getTotalPages(String m_id) {
		// 注意下一列敘述的每一個型態轉換
		totalPages = (int) (Math.ceil(getRecordCounts(m_id) / (double) recordsPerPage));
		return totalPages;
	}

}
