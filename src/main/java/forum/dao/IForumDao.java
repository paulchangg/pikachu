package forum.dao;

import java.sql.Connection;
import java.util.Set;

import forum.model.FoumBean;

public interface IForumDao {

	void insertFname(FoumBean fname);

	// 透過id找到版名
	FoumBean getF_id(int f_id);

	int getOwner_m_id();

	void setOwner_m_id(int owner_m_id);

	// 2.查詢forum表格內的所有版名不分member)
	Set<FoumBean> getAllfname(int f_id);

	// 3.本類別負責讀取資料庫內forum表格內單筆的紀錄(個別板組(owner_m_id)的資料)
	Set<FoumBean> getOwner_m_id(int owner_m_id);

	void setConnection(Connection con);

}