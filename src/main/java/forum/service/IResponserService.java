package forum.service;

import java.sql.Connection;
import java.util.List;

import forum.model.Launch_activityBean;
import forum.model.ResponserBean;

public interface IResponserService {

	String insertRescontent(int article_Id,ResponserBean responser);

	ResponserBean getRes_id(int res_id);

	int updateArticle(int article_Id, int res_id, ResponserBean responser);

	int DeleteArticle(int article_Id, int res_id);

	List<ResponserBean> getAllContent();

	List<ResponserBean> getMemberContent(String res_m_id);

	void setConnection(Connection con);

}