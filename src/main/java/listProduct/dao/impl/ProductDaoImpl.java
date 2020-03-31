package listProduct.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private int recordsPerPage = init.GlobalService.RECORDS_PER_PAGE; // 預設值：每頁三筆
	private int totalPages = -1;
	
	public ProductDaoImpl()  {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Map<Integer, ProductBean> getProduct(int pageNo) {
		Map<Integer, ProductBean> map = new HashMap<>();
		
		List<ProductBean> list = new ArrayList<ProductBean>();
        String hql = "FROM ProductBean";
        Session session = factory.getCurrentSession();
        int startRecordNo = (pageNo - 1) * recordsPerPage;

        list = session.createQuery(hql)
        			  .setFirstResult(startRecordNo)
                      .setMaxResults(recordsPerPage)
                      .getResultList();
        for(ProductBean bean : list) {
        	map.put(bean.getP_id(), bean);
        }
		return map;
	}
	
	@Override
	public ProductBean getSelectBook(int productId) {
		ProductBean bean = null;
		String hql = "FROM ProductBean b WHERE b.p_id = :product_id";
		Session session = factory.getCurrentSession();
		bean = (ProductBean)session.createQuery(hql)
					  		.setParameter("product_id", productId)
					  		.getSingleResult();
			
		return bean;
	}
	
	@Override
	public long getRecordCounts() {
		long count = 0; // 必須使用 long 型態
        String hql = "SELECT count(*) FROM ProductBean";
        Session session = factory.getCurrentSession();
        List<Long> list = session.createQuery(hql).getResultList();
        if (list.size() > 0) {
            count = list.get(0);
        }
        return count;
	}
	
	@Override
	public int getTotalPages() {
		// 注意下一列敘述的每一個型態轉換
		totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
		return totalPages;
	}

}
