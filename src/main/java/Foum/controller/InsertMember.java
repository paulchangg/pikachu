package member.controller;

import java.sql.Blob;
import java.sql.Date;

import member.model.MemberBean;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;

public class InsertMember {

	public static void main(String[] args) {
		MemberService ms = new MemberServiceImpl();
//		MemberBean mb = new MemberBean("Test","123","testtt","0987654321","test@gmail.com","tt",Date.valueOf("1997-01-01"),"m",null,"50","Taipei","大學");
//		
//		ms.saveMember(mb);
		System.out.println(ms.idExists("test"));
//		MemberBean mb = ms.queryMember("Test");
//		System.out.println(mb.toString());

	}

}
