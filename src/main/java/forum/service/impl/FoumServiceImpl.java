package forum.service.impl;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import forum.dao.IForumDao;
import forum.dao.impl.ForumDaoImpl;
import forum.model.FoumBean;
import forum.service.IFoumService;
import init.HibernateUtils;

public class FoumServiceImpl implements Serializable, IFoumService {

	private static final long serialVersionUID = 1L;

	IForumDao dao;
	SessionFactory factory;

	public FoumServiceImpl() {
		this.dao = new ForumDaoImpl();
		factory = HibernateUtils.getSessionFactory();
		;
	}

	@Override
	public void insertFname(FoumBean fname) {

		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.insertFname(fname);

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
	public FoumBean getF_id(int f_id) {
		FoumBean bean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			bean = dao.getF_id(f_id);
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
	public int getOwner_m_id() {
		
		int owner_m_id = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			owner_m_id = dao.getOwner_m_id();
			tx.commit();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return owner_m_id;
		
		
	}

	@Override
	public void setOwner_m_id(int owner_m_id) {
		
		
		dao.setOwner_m_id(owner_m_id);
	}

	@Override
	public Set<FoumBean> getAllfname(int f_id) {
		
		Set<FoumBean> set = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			set = dao.getAllfname(f_id);
			tx.commit();
		} catch (Exception ex) {

			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return set;
		
		
		
	}

	@Override
	public Set<FoumBean> getOwner_m_id(int owner_m_id) {
		
		Set<FoumBean> set = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			set = dao.getOwner_m_id(owner_m_id);
			tx.commit();
		} catch (Exception ex) {

			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}

		return set;
		
		
	}


}
