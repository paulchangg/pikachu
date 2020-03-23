package forum.service;

import java.util.Set;

import forum.model.FoumBean;

public interface IFoumService {

	void insertFname(FoumBean fname);

	FoumBean getF_id(int f_id);

	int getOwner_m_id();

	void setOwner_m_id(int owner_m_id);

	Set<FoumBean> getAllfname(int f_id);

	Set<FoumBean> getOwner_m_id(int owner_m_id);

}