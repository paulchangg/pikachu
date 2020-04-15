package forum.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import forum.dao.ILaunch_activityDao;
import forum.model.FoumBean;
import forum.model.Launch_activityBean;
import init.HibernateUtils;

//本類別
//1.新增一筆文章到launch_activity表格
//2.查詢launch_activity表格內的單筆文章(個別member)
//3.查詢launch_activity表格內的所有文章(不分member)

public class Launch_activityDaoImpl implements Serializable,ILaunch_activityDao {

	private static final long serialVersionUID = 1L;
	SessionFactory factory;
	int articleId = 0;

	public Launch_activityDaoImpl() {
		factory = HibernateUtils.getSessionFactory();

	}
	
//	@Override
//	public boolean idExists(Launch_activityBean article_Id) {
//		boolean  exist = false;
//		
//		String hql = "FROM Launch_activityBean WHERE article_Id = :articleid";
//		Session session = factory.getCurrentSession();
//		
//		try {
//			Launch_activityBean bean = (Launch_activityBean) session.createQuery(hql).setParameter("articleid", article_Id).getResultList();
//			if (bean != null) {
//				exist = true;//就是 有找到管理員的id
//			}else {
//				exist = false;//就是  一般會員
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return exist;
//	}

	// 1.新增一筆文章到launch_activity表格
	@Override
	public void insertArticle(Launch_activityBean article) {
		Session session = factory.getCurrentSession();
		
		session.save(article);
	}

	// 1-2更新某一個文章 所以用id

	@Override
	public int updateArticle(int article_Id,Launch_activityBean article) {

		int result = 0;

		
		String hql = "UPDATE Launch_activityBean   WHERE article_Id = :articleid";

		Session session = factory.getCurrentSession();

		result = session.createQuery(hql).setParameter("articleid", article_Id).executeUpdate();

		return result;
	}

	// 1-3刪某一個文章 所以用id

	@Override
	public int DeleteArticle(int article_Id) {

		int result = 0;

		String hql = "DELETE  Launch_activityBean  WHERE article_Id = :articleId";

		Session session = factory.getCurrentSession();

		result = session.createQuery(hql).setParameter("articleId", article_Id).executeUpdate();
		return result;

	}

	// 透過id找到發布的文章

	@Override
	public Launch_activityBean getArticle_Id(int article_Id) {
		Launch_activityBean article = null;
		Session session = factory.getCurrentSession();
		article = session.get(Launch_activityBean.class, article_Id);
		return article;
	}

	

	// 2.查詢launch_activity表格內的單筆文章(個別member)
	@Override
	@SuppressWarnings("unchecked")

	public List<Launch_activityBean> getMemberArticles(String article_m_id) {
		List<Launch_activityBean> list = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM Launch_activityBean lab WHERE lab.Article_m_id = :amid";
		list = session.createQuery(hql).setParameter("amid", article_m_id).getResultList();
		return list;
	}

	// 3.查詢launch_activity表格內的所有文章(不分member)
	@Override
	@SuppressWarnings("unchecked")

	public List<Launch_activityBean> getAllArticles() {
		List<Launch_activityBean> list = null;
		String hql = "FROM Launch_activityBean";
		Session session = factory.getCurrentSession();

		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public void setConnection(Connection con) {
		throw new RuntimeException("Launch_activityBeane類別不用實作此方法");
	}

	@Override
	public FoumBean getF_ById(int f_id) {
		Session session = factory.getCurrentSession();
		FoumBean foumbean = null;
		String hql = "FROM FoumBean b WHERE b.f_id = :fid" ;
		foumbean = (FoumBean) session.createQuery(hql).setParameter("fid", f_id).getSingleResult();
		return foumbean;
	}

//	@Override
//	public FoumBean getF_idByfname(String fname) {//錯誤
//		
//		FoumBean foumbean = null;
//		Session session = factory.getCurrentSession();
//		
//		foumbean = session.get(FoumBean.class, fname);
//		
//		return foumbean;
//		
//		
//	}
	
	
}
