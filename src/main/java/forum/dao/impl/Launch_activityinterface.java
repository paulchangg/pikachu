package forum.dao.impl;

import java.sql.Connection;
import java.util.List;

import forum.model.Launch_activityBean;

public interface Launch_activityinterface {

	void insertArticle(Launch_activityBean article);

	Launch_activityBean getArticle_Id(int ArticleId);

	String getArticle_m_id();

	void setArticle_m_id(String article_m_id);

	List<Launch_activityBean> getAllArticles();

	List<Launch_activityBean> getMemberArticles(String article_m_id);

	void setConnection(Connection con);

}