package forum.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import init.HibernateUtils;
import listProduct.dao.ProductDao;
import listProduct.dao.impl.ProductDaoImpl;
import listProduct.model.ProductBean;
import listProduct.service.ProductService;

public class FoumServiceImpl implements Serializable,ProductService{

	ProductDao dao;
	SessionFactory factory;
	
	public FoumServiceImpl() {
		dao = new ProductDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Map<Integer, ProductBean> getProduct(int pageNo) {
		Map<Integer, ProductBean> beans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			beans = dao.getProduct(pageNo);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return beans;
	}

	@Override
	public ProductBean getSelectBook(int productId) {
		ProductBean beans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			beans = dao.getSelectBook(productId);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return beans;
	}

	@Override
	public long getRecordCounts() {
		long count ;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			count = dao.getRecordCounts();
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return count;
	}

	@Override
	public int getTotalPages() {
		int totalPages ;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			totalPages = dao.getTotalPages();
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return totalPages;
	}

}
