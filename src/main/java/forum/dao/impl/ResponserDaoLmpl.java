package forum.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import forum.dao.IResponserDao;
import forum.model.Launch_activityBean;
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
	private String res_m_id;

	public ResponserDaoLmpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	// 1.新增某一個文章的回覆文
	@Override
	public void insertRescontent(ResponserBean responser) {

		

		Session session = factory.getCurrentSession();

		session.save(responser);
	}

	// 1.1.透過ID找到回復的內容
	@Override
	public ResponserBean getRes_id(int res_id) {
		ResponserBean res_content = null;
		Session session = factory.getCurrentSession();
		res_content = session.get(ResponserBean.class, res_id);
		return res_content;
	}

	// 1-2更新某一個文章的回覆文

	@Override
	public int updateArticle(int res_id, ResponserBean responser) {

		int result = 0;


		String hq2 = "UPDATE ResponserBean  WHERE res_id = :resid";

		Session session = factory.getCurrentSession();


		result = session.createQuery(hq2).setParameter("resid", res_id).executeUpdate();

		return result;
	}

	// 1-3刪除某一個文章的回覆文

	@Override
	public int DeleteArticle( int res_id) {


		int result = 0;


		String hq2 = "DELETE  ResponserBean  WHERE res_id = :resid";

		Session session = factory.getCurrentSession();


		result = session.createQuery(hq2).setParameter("resid", res_id).executeUpdate();

		return result;

	}

	// 2.本類別負責讀取資料庫內responser表格內全部的紀錄(不分member)
	@Override
	@SuppressWarnings("unchecked")
	public List<ResponserBean> getAllContent() {
		List<ResponserBean> list = null;
		String hql = "FROM ResponserBean";
		Session session = factory.getCurrentSession();

		list = session.createQuery(hql).getResultList();
		return list;
	}

	// 3.本類別負責讀取資料庫內responser表格內單筆的紀錄(個別member)
	@Override
	@SuppressWarnings("unchecked")
	public List<ResponserBean> getMemberContent(String res_m_id) {
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
