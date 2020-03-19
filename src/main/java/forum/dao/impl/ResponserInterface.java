package forum.dao.impl;

import java.sql.Connection;
import java.util.List;

import forum.model.ResponserBean;

public interface ResponserInterface {

	void insertRescontent(ResponserBean res_content);

	int getRes_id();

	void setRes_id(int res_id);

	ResponserBean getRes_id(int res_id);

	String getRes_m_id();

	void setRes_m_id(String res_m_id);

	List<ResponserBean> getAllContent();

	List<ResponserBean> getMemberContent(String res_m_id);

	void setConnection(Connection con);

}