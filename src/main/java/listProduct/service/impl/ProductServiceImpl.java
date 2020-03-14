package listProduct.service.impl;

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

public class ProductServiceImpl implements Serializable,ProductService{

	ProductDao dao;
	SessionFactory factory;
	
	public ProductServiceImpl() {
		dao = new ProductDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Map<Integer, ProductBean> getProduct() {
		Map<Integer, ProductBean> beans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			beans = dao.getProduct();
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return beans;
	}

}
