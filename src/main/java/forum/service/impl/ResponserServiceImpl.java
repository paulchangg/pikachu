package forum.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import forum.dao.IResponserDao;
import forum.dao.impl.ResponserDaoLmpl;
import forum.model.Launch_activityBean;
import forum.model.ResponserBean;
import forum.service.IResponserService;
import init.HibernateUtils;

public class ResponserServiceImpl implements Serializable, IResponserService {
	
	private static final long serialVersionUID = 1L;
	IResponserDao dao;
	SessionFactory factory;

	public ResponserServiceImpl() {
		this.dao = new ResponserDaoLmpl();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public String insertRescontent(Launch_activityBean article_Id,ResponserBean res_content) {
		String result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result=	dao.insertRescontent(article_Id, res_content);

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
	public ResponserBean getRes_id(int res_id) {
		return dao.getRes_id(res_id);
	}

	@Override
	public int updateArticle(Launch_activityBean article_Id,ResponserBean res_id,ResponserBean res_content) {
		
		int result = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result=	dao.updateArticle(article_Id, res_id, res_content);

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
	public int DeleteArticle(Launch_activityBean article_Id,ResponserBean res_id) {
		
		
		int result = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result=	dao.DeleteArticle(article_Id, res_id);

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
	public List<ResponserBean> getAllContent() {
		
		
		List<ResponserBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result =dao.getAllContent();

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
	public List<ResponserBean> getMemberContent(String res_m_id) {
		
		List<ResponserBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result =dao.getMemberContent(res_m_id);

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
	public void setConnection(Connection con) {
		dao.setConnection(con);
	}

}
