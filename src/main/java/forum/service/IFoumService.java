package forum.service;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import forum.model.FoumBean;
import forum.model.Launch_activityBean;
import forum.model.ResponserBean;

public interface IFoumService {
	
	
	int insertFoum(FoumBean forum);

	int DeleteFname(int f_id);

	FoumBean getF_id(int f_id);

	int getF_id();

	void setF_id(int f_id);

	List<FoumBean> getAllfname(int f_id);

	void setConnection(Connection con);

	String getFname();

	void setFname(String fname);
	
	
//	int addowner_m_id(String owner_m_id);
//	
//	
//	boolean checkid(String id);
//
//	int insertFoum(FoumBean forum);
//
//	int updateFoumBean(int f_id, FoumBean forum);
//
//	int DeleteFname(int f_id);
//
//	int DeleteFname_activityId(int f_id, int article_Id);
//
//	int DeleteFname_activityId_Res_id(int f_id, int article_Id, int res_id);
//
//	FoumBean getF_id(int f_id);
//
//	String getOwner_m_id();
//
//	void setOwner_m_id(String owner_m_id);
//
//	int getF_id();
//
//	void setF_id(int f_id);
//
//	List<FoumBean> getAllfname(int f_id);
//
//	List<FoumBean> getOwner_m_id(String owner_m_id);
//
//	void setConnection(Connection con);
//
//	String getFname();
//
//	void setFname(String fname);


}