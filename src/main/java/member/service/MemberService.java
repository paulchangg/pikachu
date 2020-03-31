package member.service;


import java.sql.Blob;

import member.model.MemberBean;

public interface MemberService {

	int saveMember(MemberBean mb);
	boolean idExists(String id);
	MemberBean queryMember(String id);
	MemberBean checkIdPassword(String userId, String password);
	public int updatePassword(String email, String newPW);
	public int changePassword(MemberBean mb, String newPW);
	public boolean emailExists(String email);
	public void sendMail(String email, String newPW);
	public int updateNickname(MemberBean mb, String nickname);
	public int updateIncome(MemberBean mb, String income);
	public int updateCity(MemberBean mb, String city);
	public int updateEducation(MemberBean mb, String education);
	public int updateM_img(MemberBean mb,Blob m_img);
}
