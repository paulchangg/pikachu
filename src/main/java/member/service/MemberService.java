package member.service;


import member.model.MemberBean;

public interface MemberService {

	int saveMember(MemberBean mb);
	boolean idExists(String id);
	MemberBean queryMember(String id);
	MemberBean checkIdPassword(String userId, String password);
	public int updatePassword(String email, String newPW);
	public boolean emailExists(String email);
	public boolean sendMail(String email, String newPW);

}
