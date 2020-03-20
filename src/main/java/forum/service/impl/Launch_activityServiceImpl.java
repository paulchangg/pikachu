package forum.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import forum.dao.ILaunch_activityDao;
import forum.dao.impl.Launch_activityDaoImpl;
import forum.model.Launch_activityBean;
import forum.service.ILaunch_activityService;
import init.HibernateUtils;

public class Launch_activityServiceImpl implements Serializable, ILaunch_activityService {
	
	
	private static final long serialVersionUID = 1L;
	
	ILaunch_activityDao dao;
	SessionFactory factory;
	
	
	public Launch_activityServiceImpl() {
		this.dao = new Launch_activityDaoImpl();
		factory = HibernateUtils.getSessionFactory();;
	}
	
	
	
	
	@Override
	public void insertArticle(Launch_activityBean article) {
		
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.insertArticle(article);

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public Launch_activityBean getArticle_Id(int ArticleId) {
		
		Launch_activityBean bean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			bean = dao.getArticle_Id(ArticleId);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}

		return bean;
		
		
	}
	@Override
	public int getArticle_m_id() {
		
		int article_m_id = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			article_m_id = dao.getArticle_m_id();
			tx.commit();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
		
		return article_m_id;
	}
	@Override
	public void setArticle_m_id(int article_m_id) {
		dao.setArticle_m_id(article_m_id);
	}
	@Override
	public List<Launch_activityBean> getAllArticles() {
		
		List<Launch_activityBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = dao.getAllArticles();
			tx.commit();
		} catch (Exception ex) {

			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return list;
		
	}
	@Override
	public List<Launch_activityBean> getMemberArticles(int article_m_id) {
		
		List<Launch_activityBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = dao.getMemberArticles(article_m_id);
			tx.commit();
		} catch (Exception ex) {

			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return list;
		
		
		
	}
}
