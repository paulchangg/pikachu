package card.service.impl;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import card.dao.CardDao;
import card.dao.impl.CardDaoImpl;
import card.model.CardBean;
import card.service.CardService;
import init.HibernateUtils;

public class CardServiceImpl implements CardService {
	
	CardDao dao;
	SessionFactory factory;
	
	public CardServiceImpl() {
		dao =  new CardDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Map<Integer, CardBean> getCbPageCards(int pageNo) {
		Map<Integer, CardBean> map = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			map = dao.getCbPageCards(pageNo);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return map;
	}

	@Override
	public Map<Integer, CardBean> getMlPageCards(int pageNo) {
		Map<Integer, CardBean> map = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			map = dao.getMlPageCards(pageNo);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return map;
	}

	@Override
	public Map<Integer, CardBean> getOilPageCards(int pageNo) {
		Map<Integer, CardBean> map = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			map = dao.getOilPageCards(pageNo);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return map;
	}

	@Override
	public Map<Integer, CardBean> getMvPageCards(int pageNo) {
		Map<Integer, CardBean> map = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			map = dao.getMvPageCards(pageNo);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return map;
	}

	@Override
	public Map<Integer, CardBean> getOsPageCards(int pageNo) {
		Map<Integer, CardBean> map = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			map = dao.getOsPageCards(pageNo);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return map;
	}

	@Override
	public Map<Integer, CardBean> getZiPageCards(int pageNo) {
		Map<Integer, CardBean> map = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			map = dao.getZiPageCards(pageNo);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return map;
	}

	@Override
	public Map<Integer, CardBean> getFgPageCards(int pageNo) {
		Map<Integer, CardBean> map = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			map = dao.getFgPageCards(pageNo);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return map;
	}

	@Override
	public CardBean getCard(int c_id) {
		CardBean bean = null;

		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			bean = dao.getCard(c_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return bean;
		
	}
	
}
