package forum.service;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import forum.model.FoumBean;
import forum.model.Launch_activityBean;
import forum.model.ResponserBean;

public interface IFoumService {

	boolean checkid(String id);

	int insertFname(FoumBean fname);

	int updateFname(FoumBean f_id, FoumBean fname);

	int DeleteFname(FoumBean f_id);

	int DeleteFname_activityId(FoumBean f_id, Launch_activityBean article_Id);

	int DeleteFname_activityId_Res_id(FoumBean f_id, Launch_activityBean article_Id, ResponserBean res_id);

	FoumBean getF_id(int f_id);

	String getOwner_m_id();

	void setOwner_m_id(String owner_m_id);

	int getF_id();

	void setF_id(int f_id);

	List<FoumBean> getAllfname(int f_id);

	List<FoumBean> getOwner_m_id(String owner_m_id);

	void setConnection(Connection con);

}