package forum.service;

import java.sql.Connection;
import java.util.List;

import forum.model.Launch_activityBean;

public interface ILaunch_activityService {
//	boolean idExists(Launch_activityBean article_Id);

	void insertArticle(Launch_activityBean article);

	int updateArticle(int article_Id,Launch_activityBean article);

	int DeleteArticle(int article_Id);

	Launch_activityBean getArticle_Id(int article_Id);

	List<Launch_activityBean> getMemberArticles(String article_m_id);

	List<Launch_activityBean> getAllArticles();

	void setConnection(Connection con);



}