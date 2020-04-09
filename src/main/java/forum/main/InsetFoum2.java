package forum.main;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import forum.model.FoumBean;
import forum.model.Launch_activityBean;
import forum.model.ResponserBean;
import forum.service.IFoumService;
import forum.service.ILaunch_activityService;
import forum.service.IResponserService;
import forum.service.impl.FoumServiceImpl;
import forum.service.impl.Launch_activityServiceImpl;
import forum.service.impl.ResponserServiceImpl;
import init.GlobalService;

public class InsetFoum2 {

	public static void main(String[] args) throws IOException, SQLException {
//1.Foum
//------------------------放入資料--------------------------------OK
		IFoumService service = new FoumServiceImpl();
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		FoumBean forum = new FoumBean(null, "旅遊", ts, null, "paul", null);
		FoumBean forum2 = new FoumBean(null, "電影", ts, null, "paul", null);

		service.insertFoum(forum);
		service.insertFoum(forum2);

//------------------------刪除資料--------------------------------ok	

//	IFoumService service = new FoumServiceImpl();
//		
//	service.DeleteFname(1);

//2.Launch_activityService
//------------------------放入資料--------------------------------OK	
//		ILaunch_activityService service2 = new Launch_activityServiceImpl();
//		Timestamp ts2 = new Timestamp(System.currentTimeMillis());
//
//		String string = "2015-10-28 18:27:45";
//		String string2 = "2019-11-15 14:11:33";
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		format.setLenient(false);
//		format2.setLenient(false);
//		Date date = null;
//		Date date2 = null;
//
//		try {
//			date = format.parse(string);
//			date2 = format2.parse(string2);
//		} catch (java.text.ParseException e) {
//			e.printStackTrace();
//		}
//
//		FoumBean foumBean = new FoumBean();
//		foumBean.setF_id(2);//版的代號
//		Blob articleimage = GlobalService.fileToBlob("D:\\資料庫功課\\Lisa02.jpg");//		Launch_activityBean article = new Launch_activityBean(null, "james", "大哥你好", "我是大哥", articleimage, "睡覺", "台北", ts2, null,
//				date, date2, 100, foumBean, null);
//
//		service2.insertArticle(article);
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
