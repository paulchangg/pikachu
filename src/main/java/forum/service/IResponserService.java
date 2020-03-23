package forum.service;

import java.util.List;

import forum.model.ResponserBean;

public interface IResponserService {

	void insertRescontent(ResponserBean res_content);

	ResponserBean getRes_id(int res_id);

	int getRes_m_id();

	void setRes_m_id(int res_m_id);

	List<ResponserBean> getAllContent();

	List<ResponserBean> getMemberContent(int res_m_id);

	void setRes_id(int res_id);

}