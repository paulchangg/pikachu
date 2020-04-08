package forum.service;

import java.sql.Connection;
import java.util.List;

import forum.model.Launch_activityBean;
import forum.model.ResponserBean;

public interface IResponserService {

	String insertRescontent(Launch_activityBean article_Id, ResponserBean res_content);

	ResponserBean getRes_id(int res_id);

	int updateArticle(Launch_activityBean article_Id, ResponserBean res_id, ResponserBean res_content);

	int DeleteArticle(Launch_activityBean article_Id, ResponserBean res_id);

	List<ResponserBean> getAllContent();

	List<ResponserBean> getMemberContent(String res_m_id);

	void setConnection(Connection con);

}