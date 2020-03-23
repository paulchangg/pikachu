package forum.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import forum.dao.IForumDao;
import forum.model.FoumBean;
import init.HibernateUtils;

//1.本類別新增版名到forum表格
//2.本類別負責讀取資料庫內forum表格內全部的紀錄(不分member)
//3.本類別負責讀取資料庫內forum表格內單筆的紀錄(個別板組(owner_m_id)的資料)

public class ForumDaoImpl implements Serializable, IForumDao {
	private static final long serialVersionUID = 1L;

	SessionFactory factory;
	private String fname = null;
	private int owner_m_id = 0;
	private int f_id = 0;

	public ForumDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	// 1.本類別新增版名到forum表格

	@Override
	public void insertFname(FoumBean fname) {
		Session session = factory.getCurrentSession();
		session.persist(fname);
	}

	// 透過id(流水號)找到版名
	@Override
	public FoumBean getF_id(int f_id) {
		FoumBean fname = null;
		Session session = factory.getCurrentSession();

		fname = session.get(FoumBean.class, f_id);

		return fname;

	}

	@Override
	public int getOwner_m_id() {
		return owner_m_id;
	}

	@Override
	public void setOwner_m_id(int owner_m_id) {
		this.owner_m_id = owner_m_id;
	}
	
	// 2.查詢forum表格內的所有版名不分member)
	@Override
	@SuppressWarnings("unchecked")
	
	public Set<FoumBean> getAllfname(int f_id) {
		
		Set<FoumBean> set = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM FoumBean ";
		
		set = (Set<FoumBean>) session.createQuery(hql).getResultList();
		
		return set;
		
	}

	// 3.本類別負責讀取資料庫內forum表格內單筆的紀錄(個別板組(owner_m_id)的資料)
	@Override
	@SuppressWarnings("unchecked")

	public Set<FoumBean> getOwner_m_id(int owner_m_id) {

		Set<FoumBean> set = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM FoumBean fob WHERE fob.owner_m_id =:owid";

		set = (Set<FoumBean>) session.createQuery(hql).setParameter("owid", owner_m_id).getResultList();
		return set;

	}


	@Override
	public void setConnection(Connection con) {
		throw new RuntimeException("Launch_activityBeane類別不用實作此方法");
	}

}
