package member.service;


import java.sql.Blob;
import java.util.List;

import forum.model.Launch_activityBean;
import member.model.MemberBean;

public interface MemberService {

	int saveMember(MemberBean mb);
	boolean idExists(String id);
	MemberBean queryMember(String id);
	MemberBean checkIdPassword(String userId, String password);
	public int updatePassword(String email, String newPW);
	public void changePassword(MemberBean mb);
	public boolean emailExists(String email);
	public void sendMail(String email, String newPW);
	public int updateM_img(MemberBean mb,Blob m_img);
	public void updateMember(MemberBean mb);
	void addMyActivity(Launch_activityBean article_Id, String m_id);
	public List<MemberBean> getActivityPerson(int article_Id);
	void leaveMyActivity(Launch_activityBean article_Id, String m_id);
	
	
}
