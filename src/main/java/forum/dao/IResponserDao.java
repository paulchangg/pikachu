package forum.dao;

import java.sql.Connection;
import java.util.List;

import forum.model.ResponserBean;

public interface IResponserDao {

	//1.本類別新增回復到responser表格
	void insertRescontent(ResponserBean res_content);

	int getRes_id();

	void setRes_id(int res_id);

	//1.1.透過ID找到回復的內容
	ResponserBean getRes_id(int res_id);

	int getRes_m_id();

	void setRes_m_id(int res_m_id);

	//2.本類別負責讀取資料庫內responser表格內全部的紀錄(不分member)
	List<ResponserBean> getAllContent();

	//3.本類別負責讀取資料庫內responser表格內單筆的紀錄(個別member)
	List<ResponserBean> getMemberContent(int res_m_id);

	void setConnection(Connection con);

}