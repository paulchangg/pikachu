package member.dao;

import java.sql.Blob;

import member.model.MemberBean;

public interface MemberDao {

	public int saveMember(MemberBean mb);

	public boolean idExists(String id);

	public MemberBean queryMember(String id);

	public MemberBean checkIdPassword(String userId, String password);
	
	public int updatePassword(String email, String newPW);

	public void changePassword(MemberBean mb);
	
	public boolean emailExists(String email);
	
	public int updateM_img(MemberBean mb,Blob m_img);

	public void updateMember(MemberBean mb);

}
