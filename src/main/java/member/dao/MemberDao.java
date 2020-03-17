package member.dao;

import member.model.MemberBean;

public interface MemberDao {

	int saveMember(MemberBean mb);

	public boolean idExists(String id);

	public MemberBean queryMember(String id);

	public MemberBean checkIdPassword(String userId, String password);

}
