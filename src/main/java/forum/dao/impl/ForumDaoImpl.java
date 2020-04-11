package forum.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import forum.dao.IForumDao;
import forum.model.FoumBean;
import init.HibernateUtils;

//1.本類別新增版名到forum表格
//2.本類別負責讀取資料庫內forum表格內全部的紀錄(不分管理員member)
//3.本類別負責讀取資料庫內forum表格內單筆的紀錄(個別管理員(owner_m_id)的資料)

public class ForumDaoImpl implements Serializable, IForumDao {

	private static final long serialVersionUID = 1L;

	SessionFactory factory;
	private String fname = null;
//	private String owner_m_id;// 這是管理員的意思
	private int f_id = 0;

	public ForumDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	// 新增管理員owner_m_id到forum表格
//	@Override
//	public int addowner_m_id(String owner_m_id) {
//		int n = 0;
//		Session session = factory.getCurrentSession();
//		session.save(owner_m_id);
//		n++;
//		return n;
//	}
	// 判斷id是否是管理員的owner_m_id 如果是，傳回true，表示此id可以行使管理員權力
	// 否則傳回false，表示此id沒有管理員權限。

//	@Override
//	public boolean checkid(String id) {
//		boolean exist = false;
//		String hql = "FROM FoumBean WHERE owner_m_id = :omid ";
//		Session session = factory.getCurrentSession();
//
//		try {
//			FoumBean bean = (FoumBean) session.createQuery(hql).setParameter("omid", id).getResultList();
//			if (bean != null) {
//				exist = true;// 就是 有找到管理員的id
//			} else {
//				exist = false;// 就是 一般會員
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return exist;
//
//	}

	// 1.新增版名到forum表格，所有資訊
	@Override
	public int insertFoum(FoumBean forum) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(forum);
		n++;
		return n;
	}

	// 1-2更新版名到forum表格

//	@Override
//	public int updateFoumBean(int f_id, FoumBean forum) {
//		int result = 0;
//
//		String hql = "UPDATE FoumBean  WHERE f_id = :fid";
//
//		Session session = factory.getCurrentSession();
//
//		result = session.createQuery(hql).setParameter("fid", f_id).executeUpdate();
//
//		return result;
//	}

	// 1-3刪除看板
	@Override
	public int DeleteFname(int f_id) {
		int result = 0;

		String hql = "DELETE FoumBean  WHERE f_id = :fid";

		Session session = factory.getCurrentSession();

		result = session.createQuery(hql).setParameter("fid", f_id).executeUpdate();
		return result;

	}

	// 1-4刪除看板內特定文章
//	@Override
//	public int DeleteFname_activityId(int f_id, int article_Id) {
//
//		FoumBean fob = null;
//		int result = 0;
//		String hql1 = "FROM FoumBean  WHERE f_id = :fid";
//
//		String hql2 = "DELETE  Launch_activityBean WHERE article_Id = :articleid";
//
//		Session session = factory.getCurrentSession();
//
//		try {
//			fob = (FoumBean) session.createQuery(hql1).setParameter("fid", f_id).getSingleResult();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		result = session.createQuery(hql2).setParameter("articleid", article_Id).executeUpdate();
//		return result;
//	}

	// 1-5刪除看板內特定文章的回覆

//	@Override

//	public int DeleteFname_activityId_Res_id(int f_id, int article_Id, int res_id) {
//
//		FoumBean fob1 = null;
//		Launch_activityBean lab1 = null;
//		int result = 0;
//
//		String hql1 = "FROM FoumBean  WHERE f_id = :fid";
//
//		String hql2 = "FORM Launch_activityBean WHERE article_Id = :articleid";
//
//		String hql3 = "DELETE ResponserBean WHERE res_id = :resid";
//		Session session = factory.getCurrentSession();
//
//		try {
//			fob1 = (FoumBean) session.createQuery(hql1).setParameter("fid", f_id).getSingleResult();
//			lab1 = (Launch_activityBean) session.createQuery(hql2).setParameter("articleid", article_Id)
//					.getSingleResult();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		result = session.createQuery(hql3).setParameter("resid", res_id).executeUpdate();
//
//		return result;
//	}

	// 透過id(流水號)找到版名
	@Override
	public FoumBean getF_id(int f_id) {
		FoumBean fname = null;
		Session session = factory.getCurrentSession();

		fname = session.get(FoumBean.class, f_id);

		return fname;

	}

//	@Override
//	public String getOwner_m_id() {
//		return owner_m_id;
//	}
//
//	@Override
//	public void setOwner_m_id(String owner_m_id) {
//		this.owner_m_id = owner_m_id;
//	}

	@Override
	public int getF_id() {
		return f_id;
	}

	@Override
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	// 2.查詢forum表格內的所有版名不分member)
	@Override
	@SuppressWarnings("unchecked")

	public List<FoumBean> getAllfname(int f_id) {

		List<FoumBean> list = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM FoumBean ";

		list = (List<FoumBean>) session.createQuery(hql).getResultList();

		return list;

	}

	// 3.本類別負責讀取資料庫內forum表格內單筆的紀錄(個別板組(owner_m_id)的資料)
//	@Override
//	@SuppressWarnings("unchecked")

//	public List<FoumBean> getOwner_m_id(String owner_m_id) {
//
//		List<FoumBean> list = null;
//		Session session = factory.getCurrentSession();
//		String hql = "FROM FoumBean fob WHERE fob.owner_m_id =:owid";
//
//		list = (List<FoumBean>) session.createQuery(hql).setParameter("owid", owner_m_id).getResultList();
//		return list;
//
//	}

	@Override
	public void setConnection(Connection con) {
		throw new RuntimeException("Launch_activityBeane類別不用實作此方法");
	}

	@Override
	public String getFname() {
		return fname;
	}

	@Override
	public void setFname(String fname) {
		this.fname = fname;
	}

//	@Override
//	public int addowner_m_id(String owner_m_id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

//	@Override
//	public boolean checkid(String id) {
//		// TODO Auto-generated method stub
//		return false;
//	}

//	@Override
//	public int updateFoumBean(int f_id, FoumBean forum) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int DeleteFname_activityId(int f_id, int article_Id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int DeleteFname_activityId_Res_id(int f_id, int article_Id, int res_id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public String getOwner_m_id() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
