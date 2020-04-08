package forum.dao;

import java.sql.Connection;
import java.util.List;

import forum.model.Launch_activityBean;

public interface ILaunch_activityDao {
//	boolean idExists(Launch_activityBean article_Id);
	// 1.新增一筆文章到launch_activity表格
	void insertArticle(Launch_activityBean article);

	int updateArticle(int article_Id,Launch_activityBean article);

	int DeleteArticle(int article_Id);

	Launch_activityBean getArticle_Id(int article_Id);



	// 2.查詢launch_activity表格內的單筆文章(個別member)
	List<Launch_activityBean> getMemberArticles(String article_m_id);

	// 3.查詢launch_activity表格內的所有文章(不分member)
	List<Launch_activityBean> getAllArticles();

	void setConnection(Connection con);

	


}