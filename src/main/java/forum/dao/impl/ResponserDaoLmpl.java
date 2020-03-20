package forum.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import forum.dao.IResponserDao;
import forum.model.ResponserBean;
import init.HibernateUtils;
//1.本類別新增回復到responser表格
//-----------------------------------------------------------------
//2.本類別負責讀取資料庫內responser表格內全部的紀錄(不分member)
//3.本類別負責讀取資料庫內responser表格內單筆的紀錄(個別member)
//-----------------------------------------------------------------
public class ResponserDaoLmpl implements Serializable, IResponserDao {
	private static final long serialVersionUID = 1L;
	SessionFactory factory;
	private int res_id = 0;
	private int res_m_id = 0;

	public ResponserDaoLmpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	//1.本類別新增回復到responser表格
	@Override
	public void insertRescontent(ResponserBean res_content) {
		Session session = factory.getCurrentSession();
		session.persist(res_content);
	}

	@Override
	public int getRes_id() {
		return res_id;
	}

	@Override
	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}
	//1.1.透過ID找到回復的內容
	@Override
	public ResponserBean getRes_id(int res_id) {
		ResponserBean res_content = null;
		Session session = factory.getCurrentSession();
		res_content = session.get(ResponserBean.class, res_id);
		return res_content;
	}

	
	

	@Override
	public int getRes_m_id() {
		return res_m_id;
	}
	@Override
	public void setRes_m_id(int res_m_id) {
		this.res_m_id = res_m_id;
	}
	//2.本類別負責讀取資料庫內responser表格內全部的紀錄(不分member)
	@SuppressWarnings("unchecked")
	@Override
	public List<ResponserBean> getAllContent() {
		List<ResponserBean> list = null;
		String hql = "FROM ResponserBean";
		Session session = factory.getCurrentSession();

		list = session.createQuery(hql).getResultList();
		return list;
	}
	//3.本類別負責讀取資料庫內responser表格內單筆的紀錄(個別member)
	@SuppressWarnings("unchecked")
	@Override
	public List<ResponserBean> getMemberContent(int res_m_id) {
		List<ResponserBean> list = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM ResponserBean rsb WHERE rsb.res_m_id = :rsmid";
		list = session.createQuery(hql).setParameter("rsmid", res_m_id).getResultList();
		return list;
	}

	
	
	@Override
	public void setConnection(Connection con) {
		throw new RuntimeException("ResponserBean類別不用實作此方法");
	}

}
