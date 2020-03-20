package forum.service;

import java.util.List;

import forum.model.Launch_activityBean;

public interface ILaunch_activityService {

	void insertArticle(Launch_activityBean article);

	Launch_activityBean getArticle_Id(int ArticleId);

	int getArticle_m_id();

	void setArticle_m_id(int article_m_id);

	List<Launch_activityBean> getAllArticles();

	List<Launch_activityBean> getMemberArticles(int article_m_id);

}