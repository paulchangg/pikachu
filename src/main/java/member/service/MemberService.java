package member.service;


import member.model.MemberBean;

public interface MemberService {

	void saveMember(MemberBean mb);
	boolean idExists(String id);
	MemberBean queryMember(String id);
	MemberBean checkIdPassword(String userId, String password);

}
