package member.controller;

//失敗
import java.sql.Date;
import java.sql.Timestamp;

import member.model.MemberBean;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;

public class InsertMember {

	public static void main(String[] args) {

		String[] id = { "james", "paul", "Peter", "Danny", "Weld", "Lily", "Davie", "Pig", "Happy", "Kitty", "Cindy" };
		String[] password = { "james", "paul", "Peter", "Danny", "Weld", "Lily", "Davie", "Pig", "Happy", "Kitty",
				"Cindy" };
		String[] name = { "james", "paul", "Peter", "Danny", "Weld", "Lily", "Davie", "Pig", "Happy", "Kitty",
				"Cindy" };
		String[] phone_num = { "0933868789", "0933868789", "0933868789", "0933868789", "0933868789", "0933868789",
				"0933868789", "0933868789", "0933868789", "0933868789", "0933868789" };
		String[] mail = { "james@@", "paul@@", "Peter@@", "Danny@@", "Weld@@", "Lily@@", "Davie@@", "Pig@@", "Happy@@",
				"Kitty@@", "Cindy@@" };
		String[] gender = { "Male", "Male", "Male", "Male", "Male", "Lily", "Male", "Male", "Male", "Female",
				"Female" };
		String[] city = { "Taipei", "Taipei", "Taipei", "Taipei", "Taipei", "Taipei", "Taipei", "Taipei", "Taipei",
				"Taipei", "Taipei" };

		String birthdatStr = "1911-01-01";

		Date birthday = null;
		try {
			birthday = java.sql.Date.valueOf(birthdatStr);
		} catch (Exception e) {

		}
		MemberService ms = new MemberServiceImpl();
		MemberBean mb = new MemberBean();
		
		
		

		for (int n = 0; n < phone_num.length; n++) {
			for (String m_id : id) {
				System.out.println(m_id);
				for (String m_password : password) {
					for (String m_name : name) {
						for (String m_phone_num : phone_num) {
							for (String m_mail : mail) {

								for (String m_gender : gender) {
									for (String m_city : city) {
										mb.setCity(m_city);
										mb.setBirthday(birthday);
//										mb.setM_id(m_id);
//										mb.setM_password(m_password);
//										mb.setName(m_name);
//										mb.setM_mail(m_mail);
//										mb.setPhone_num(m_phone_num);
//										mb.setGender(m_gender);
//										mb.setCity(m_city);
//										mb.setBirthday(birthday);
//										ms.saveMember(mb);
									}
//									mb.setGender(m_gender);
								}
//								mb.setM_mail(m_mail);
							}
//							mb.setPhone_num(m_phone_num);
						}
//						mb.setName(m_name);
					}
//					mb.setM_password(m_password);
				}
//				System.out.println(m_id);
//				mb.setM_id(m_id);
				
			}
			
//			ms.saveMember(mb);
			break;
		}
	}
}
