package listProduct.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import init.HibernateUtils;
import listProduct.dao.ProductDao;
import listProduct.model.ProductBean;

public class ProductDaoImpl implements Serializable, ProductDao{

	SessionFactory factory;
	
	public ProductDaoImpl()  {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Map<Integer, ProductBean> getProduct() {
		Map<Integer, ProductBean> map = new HashMap<>();
		
		List<ProductBean> list = new ArrayList<ProductBean>();
        String hql = "FROM ProductBean";
        Session session = factory.getCurrentSession();


        list = session.createQuery(hql)
                      .getResultList();
        for(ProductBean bean : list) {
        	map.put(bean.getP_id(), bean);
        }
		return map;
	}

}
