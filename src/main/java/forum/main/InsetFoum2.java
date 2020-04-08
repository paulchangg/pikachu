package forum.main;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import forum.model.FoumBean;
import forum.service.IFoumService;
import forum.service.impl.FoumServiceImpl;
import member.model.MemberBean;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;

public class InsetFoum2 {

	public static void main(String[] args) throws IOException, SQLException {
		
		FoumBean foumbean = new FoumBean();
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		foumbean.setFname("電影");	
		foumbean.setStartdate_datetime(ts);
		foumbean.setUpdate_datetime(java.sql.Date.valueOf("2020-04-26"));
		MemberService ms = new MemberServiceImpl();
		MemberBean memberBean = ms.queryMember("kitty123");
		
		foumbean.setOwner_m_id(memberBean);
				
		IFoumService foumservice = new FoumServiceImpl();
		
		foumservice.insertFname(foumbean);
		foumservice.addowner_m_id(foumbean);
		
		
		
		
		
		
		
//		ILaunch_activityService la = new Launch_activityServiceImpl();
//		List list = la.getMemberArticles("mickey");
//		Launch_activityBean l = (Launch_activityBean)list.get(0);
//		System.out.println("看板="+l.getFoumBean().getFname());
//		System.out.println(l.toString());

	}

}
