package forum.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import forum.model.FoumBean;
import forum.model.Launch_activityBean;
import forum.model.ResponserBean;

public interface IForumDao {

	boolean checkid(String id);

	// 1.新增版名到forum表格
	int insertFname(FoumBean fname);
	// 1-2更新版名到forum表格
	int updateFname(FoumBean f_id,FoumBean fname);

	// 1-3刪除看板
	int DeleteFname(FoumBean f_id);

	// 1-4刪除看板內特定文章
	int DeleteFname_activityId(FoumBean f_id, Launch_activityBean article_Id);

	int DeleteFname_activityId_Res_id(FoumBean f_id, Launch_activityBean article_Id, ResponserBean res_id);

	// 透過id(流水號)找到版名
	FoumBean getF_id(int f_id);

	String getOwner_m_id();

	void setOwner_m_id(String owner_m_id);

	int getF_id();

	void setF_id(int f_id);

	// 2.查詢forum表格內的所有版名不分member)
	List<FoumBean> getAllfname(int f_id);

	// 3.本類別負責讀取資料庫內forum表格內單筆的紀錄(個別板組(owner_m_id)的資料)
	List<FoumBean> getOwner_m_id(String owner_m_id);

	void setConnection(Connection con);

	

}