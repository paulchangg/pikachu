package forum.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import forum.dao.ILaunch_activityDao;
import forum.model.Launch_activityBean;
import init.HibernateUtils;

//本類別
//1.新增一筆文章到launch_activity表格
//2.查詢launch_activity表格內的單筆文章(個別member)
//3.查詢launch_activity表格內的所有文章(不分member)

public class Launch_activityDaoImpl implements Serializable, ILaunch_activityDao {

	private static final long serialVersionUID = 1L;
	private int Article_m_id = 0;
	SessionFactory factory;
	int ArticleId = 0;

	public Launch_activityDaoImpl() {
		factory = HibernateUtils.getSessionFactory();

	}

	// 1.新增一筆文章到launch_activity表格
	@Override
	public void insertArticle(Launch_activityBean article) {
		Session session = factory.getCurrentSession();
		session.persist(article);
	}

	// 透過id找到發布的文章
	@Override
	public Launch_activityBean getArticle_Id(int ArticleId) {
		Launch_activityBean article = null;
		Session session = factory.getCurrentSession();
		article = session.get(Launch_activityBean.class, ArticleId);
		return article;
	}

	@Override
	public int getArticle_m_id() {
		return Article_m_id;
	}

	@Override
	public void setArticle_m_id(int article_m_id) {
		Article_m_id = article_m_id;
	}

	// 3.查詢launch_activity表格內的所有文章(不分member)
	@SuppressWarnings("unchecked")
	@Override
	public List<Launch_activityBean> getAllArticles() {
		List<Launch_activityBean> list = null;
		String hql = "FROM Launch_activityBean";
		Session session = factory.getCurrentSession();

		list = session.createQuery(hql).getResultList();
		return list;
	}

	// 2.查詢launch_activity表格內的單筆文章(個別member)
	@SuppressWarnings("unchecked")
	@Override
	public List<Launch_activityBean> getMemberArticles(int article_m_id) {
		List<Launch_activityBean> list = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM Launch_activityBean lab WHERE lab.article_m_id = :amid";
		list = session.createQuery(hql).setParameter("amid", article_m_id).getResultList();
		return list;
	}

	@Override
	public void setConnection(Connection con) {
		throw new RuntimeException("Launch_activityBeane類別不用實作此方法");
	}
}
