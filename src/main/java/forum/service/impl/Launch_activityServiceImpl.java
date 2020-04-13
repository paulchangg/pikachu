package forum.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import forum.dao.ILaunch_activityDao;
import forum.dao.impl.Launch_activityDaoImpl;
import forum.model.FoumBean;
import forum.model.Launch_activityBean;
import forum.service.ILaunch_activityService;
import init.HibernateUtils;

public class Launch_activityServiceImpl implements Serializable, ILaunch_activityService {

	private static final long serialVersionUID = 1L;

	ILaunch_activityDao dao;
	SessionFactory factory;

	public Launch_activityServiceImpl() {
		this.dao = new Launch_activityDaoImpl();
		factory = HibernateUtils.getSessionFactory();

	}

//	@Override
//	public boolean idExists(Launch_activityBean article_Id) {
//		
//		boolean result = false;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			result = dao.idExists(article_Id);
//
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//		
//		return result;
//	}

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
	public int updateArticle(int article_Id, Launch_activityBean article) {

		int result = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = dao.updateArticle(article_Id, article);

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return result;

	}

	@Override
	public int DeleteArticle(int article_Id) {

		int result = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = dao.DeleteArticle(article_Id);

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return result;

	}

	@Override
	public Launch_activityBean getArticle_Id(int article_Id) {
		return dao.getArticle_Id(article_Id);
	}

	@Override
	public List<Launch_activityBean> getMemberArticles(String article_m_id) {

		List<Launch_activityBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			result = dao.getMemberArticles(article_m_id);

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return result;

	}

	@Override
	public List<Launch_activityBean> getAllArticles() {

		List<Launch_activityBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = dao.getAllArticles();

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return result;

	}

//	@Override
//	public FoumBean getF_ById(int f_id) {
//		Session session = factory.getCurrentSession();
//		return null;
//	}

	@Override
	public void setConnection(Connection con) {
		dao.setConnection(con);
	}

	@Override
	public FoumBean getF_ById(int f_id) {
		FoumBean foumbean =null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			foumbean = dao.getF_ById(f_id);

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return foumbean;
		
	}

}
