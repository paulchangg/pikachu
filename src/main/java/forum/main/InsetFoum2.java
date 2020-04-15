package forum.main;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import forum.model.FoumBean;
import forum.model.Launch_activityBean;
import forum.service.IFoumService;
import forum.service.ILaunch_activityService;
import forum.service.impl.FoumServiceImpl;
import forum.service.impl.Launch_activityServiceImpl;
import init.GlobalService;
import member.model.MemberBean;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;

public class InsetFoum2 {

	public static void main(String[] args) throws IOException, SQLException {
//1.Foum
//------------------------放入資料--------------------------------OK

//		String [] n= {"旅遊","魯蛇卡","加油回饋","無限卡","電影","購物","現金回饋","宗教","公益","鈦金or御璽卡","運動"};
//		for(String fname:n) {  // = for(int i =0; i<n.length;i++)
//		IFoumService service = new FoumServiceImpl();
//		FoumBean forum = new FoumBean(null, fname, null);
//		service.insertFoum(forum);		
//		}		
//-------------------------------------------------------------------

//		IFoumService service = new FoumServiceImpl();
//
//		
//		List<FoumBean> list = service.getAllfname(0);
//		
//		FoumBean foumbean = null;
//		
//		for (int n = 0; n < 11; n++) {
//			
//			foumbean = list.get(n);
////			System.out.println("看板名:" + foumbean.getFname());
//			
//			String fname = foumbean.getFname();
//			
//			int f_id = foumbean.getF_id();
//			
//			System.out.println("板編號:" +f_id+"     "+"看板名:" +fname);
//			
//
//			
//			
//		}



//------------------------刪除資料--------------------------------ok	

//	IFoumService service = new FoumServiceImpl();
//		
//	service.DeleteFname(1);

//2.Launch_activityService
//------------------------放入資料--------------------------------OK	
		ILaunch_activityService service2 = new Launch_activityServiceImpl();
		Timestamp ts2 = new Timestamp(System.currentTimeMillis());
//
		String string = "2015-10-28 18:27:45";
		String string2 = "2019-11-15 14:11:33";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setLenient(false);
		format2.setLenient(false);
		Date date = null;
		Date date2 = null;
//
		try {
			date = format.parse(string);
			date2 = format2.parse(string2);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
//
		FoumBean foumBean = new FoumBean();
		foumBean.setF_id(10);//版的代號
		Blob articleimage = GlobalService.fileToBlob("C:\\jack01.jpg");
		Launch_activityBean article = new Launch_activityBean(null, "james99", "大哥你好", "我是大哥", articleimage, "睡覺", "台北", ts2, null,
				date, date2, 100, foumBean, null);

		service2.insertArticle(article);
////		

//------------------------刪除資料--------------------------------ok	
//ILaunch_activityService service = new Launch_activityServiceImpl();
//		service.DeleteArticle(1);
//		

//3.Responser	
//------------------------放入資料--------------------------------OK	
//		IResponserService service3 = new ResponserServiceImpl();
//		Timestamp ts3 = new Timestamp(System.currentTimeMillis());
//		Launch_activityBean launch_activityBean = new Launch_activityBean();
//		launch_activityBean.setArticle_Id(2);
//		ResponserBean responser = new ResponserBean(null, "Paul", ts3, null, "大哥是對的", launch_activityBean);
//
//		service3.insertRescontent(responser);
//------------------------刪除資料--------------------------------OK
//		IResponserService service = new ResponserServiceImpl();
//		service.DeleteArticle(4);

	}

}
