package member.dao;

import member.model.MemberBean;

public interface MemberDao {

	public int saveMember(MemberBean mb);

	public boolean idExists(String id);

	public MemberBean queryMember(String id);

	public MemberBean checkIdPassword(String userId, String password);
	
	public int updatePassword(String email, String newPW);

	public int changePassword(MemberBean mb, String newPW);
	
	public boolean emailExists(String email);

	public int updateNickname(MemberBean mb, String nickname);

	public int updateIncome(MemberBean mb, String income);

	public int updateCity(MemberBean mb, String city);

	public int updateEducation(MemberBean mb, String education);


}
