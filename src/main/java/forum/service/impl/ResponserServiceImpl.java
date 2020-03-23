package forum.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import forum.dao.IResponserDao;
import forum.dao.impl.ResponserDaoLmpl;
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
	public void insertRescontent(ResponserBean res_content) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.insertRescontent(res_content);

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
	public ResponserBean getRes_id(int res_id) {
		ResponserBean bean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			bean = dao.getRes_id(res_id);
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
	public int getRes_m_id() {
		int res_m_id = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			res_m_id = dao.getRes_m_id();
			tx.commit();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return res_m_id;
	}

	@Override
	public void setRes_m_id(int res_m_id) {
		dao.setRes_m_id(res_m_id);
	}

	@Override
	public List<ResponserBean> getAllContent() {
		List<ResponserBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = dao.getAllContent();
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
	public List<ResponserBean> getMemberContent(int res_m_id) {

		List<ResponserBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = dao.getMemberContent(res_m_id);
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
	public void setRes_id(int res_id) {
		dao.setRes_id(res_id);
	}

}
