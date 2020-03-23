package member.dao;

import member.model.MemberBean;

public interface MemberDao {

	public int saveMember(MemberBean mb);

	public boolean idExists(String id);

	public MemberBean queryMember(String id);

	public MemberBean checkIdPassword(String userId, String password);
	
	public int updatePassword(String email, String newPW);
	
	public boolean emailExists(String email);

}
