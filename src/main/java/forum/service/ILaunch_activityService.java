package forum.service;

import java.sql.Connection;
import java.util.List;

import forum.model.Launch_activityBean;

public interface ILaunch_activityService {

	void insertArticle(Launch_activityBean article);

	int updateArticle(Launch_activityBean article_Id);

	int DeleteArticle(Launch_activityBean article_Id);

	Launch_activityBean getArticle_Id(int articleId);

	String getArticle_m_id();

	void setArticle_m_id(String article_m_id);

	List<Launch_activityBean> getMemberArticles(String article_m_id);

	List<Launch_activityBean> getAllArticles();

	void setConnection(Connection con);

}