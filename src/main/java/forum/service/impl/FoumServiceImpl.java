package forum.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import forum.dao.IForumDao;
import forum.dao.impl.ForumDaoImpl;
import forum.model.FoumBean;
import forum.model.Launch_activityBean;
import forum.model.ResponserBean;
import forum.service.IFoumService;
import init.HibernateUtils;

public class FoumServiceImpl implements Serializable, IFoumService {

	private static final long serialVersionUID = 1L;

	IForumDao dao;
	SessionFactory factory;

	public FoumServiceImpl() {
		this.dao = new ForumDaoImpl();
		factory = HibernateUtils.getSessionFactory();

	}

	@Override
	public boolean checkid(String id) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = dao.checkid(id);

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
	public int insertFname(FoumBean fname) {

		int result = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = dao.insertFname(fname);

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
	public int updateFname(FoumBean f_id, FoumBean fname) {
		int result = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = dao.updateFname(f_id, fname);

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
	public int DeleteFname(FoumBean f_id) {

		int result = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = dao.DeleteFname(f_id);

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
	public int DeleteFname_activityId(FoumBean f_id, Launch_activityBean article_Id) {

		int result = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = dao.DeleteFname_activityId(f_id, article_Id);

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
	public int DeleteFname_activityId_Res_id(FoumBean f_id, Launch_activityBean article_Id, ResponserBean res_id) {

		int result = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = dao.DeleteFname_activityId_Res_id(f_id, article_Id, res_id);

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
	public FoumBean getF_id(int f_id) {
		return dao.getF_id(f_id);
	}

	@Override
	public String getOwner_m_id() {
		return dao.getOwner_m_id();
	}

	@Override
	public void setOwner_m_id(String owner_m_id) {
		dao.setOwner_m_id(owner_m_id);
	}

	@Override
	public int getF_id() {
		return dao.getF_id();
	}

	@Override
	public void setF_id(int f_id) {
		dao.setF_id(f_id);
	}

	@Override
	public List<FoumBean> getAllfname(int f_id) {

		List<FoumBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = dao.getAllfname(f_id);

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
	public List<FoumBean> getOwner_m_id(String owner_m_id) {

		List<FoumBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = dao.getOwner_m_id(owner_m_id);

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
