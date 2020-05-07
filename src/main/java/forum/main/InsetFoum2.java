package forum.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import forum.model.FoumBean;
import forum.service.IFoumService;
import forum.service.impl.FoumServiceImpl;

public class InsetFoum2 {

	public static void main(String[] args) throws IOException, SQLException {

//1.Foum
//------------------------放入資料--------------------------------OK

		String [] n= {"旅遊","魯蛇卡","加油回饋","無限卡","電影","購物","現金回饋","宗教","公益","鈦金or御璽卡","運動"};
		for(String fname:n) {  // = for(int i =0; i<n.length;i++)
		IFoumService service = new FoumServiceImpl();
		FoumBean forum = new FoumBean(null, fname, null);
		service.insertFoum(forum);		
		}	

//		List<String> list = new ArrayList<String>();
//		//已經參加活動的會員id
//		list.add("123");
//		list.add("234");
//		list.add("456");
//		
//		String t = "999";//登入的會員id
//		int n =0;
//		
//		if(!list.contains(t)) {
//			n++;
//		}
//		
//		System.out.println("n"+n);
		
		
		//目的: 已經參加活動的會員id  裡面沒有  登入的會員id  才++ 如果有的話  不要做++的事情
		
		
		

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
//		ILaunch_activityService service2 = new Launch_activityServiceImpl();
//		Timestamp ts2 = new Timestamp(System.currentTimeMillis());
////
//		String string = "2015-10-28 18:27:45";
//		String string2 = "2019-11-15 14:11:33";
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		format.setLenient(false);
//		format2.setLenient(false);
//		Date date = null;
//		Date date2 = null;
////
//		try {
//			date = format.parse(string);
//			date2 = format2.parse(string2);
//		} catch (java.text.ParseException e) {
//			e.printStackTrace();
//		}
////
//		FoumBean foumBean = new FoumBean();
//		foumBean.setF_id(10);//版的代號
//		Blob articleimage = GlobalService.fileToBlob("C:\\jack01.jpg");
//		Launch_activityBean article = new Launch_activityBean(null, "james99", "大哥你好", "我是大哥", articleimage, "睡覺", "台北", ts2, null,
//				date, date2, 100, foumBean, null);
//
//		service2.insertArticle(article);
//-----------------------查詢會員發布的資料------------------------------------------------------

//		Collection<Number> c = new ArrayList<>();
//		double sum = 0 ;
//		c.add(new Integer(123));
//		c.add(new Integer(456));
//		c.add(new Double(3.14));
//		c.add(new Integer(456));
//		for(Number o: c){
//		sum += o.doubleValue();
//		}

//		List<Coupon> couponList = new ArrayList<>();
//        Coupon coupon1 = new Coupon(1,100,"优惠券1");
//        Coupon coupon2 = new Coupon(2,200,"优惠券2");
//        Coupon coupon3 = new Coupon(3,300,"优惠券3");
//        couponList.add(coupon1);
//        couponList.add(coupon2);
//        couponList.add(coupon3);
//
//        List<Integer> resultList = couponList.stream().map(Coupon::getCouponId).collect(Collectors.toList());
//        System.out.println(resultList);

//		
//		String article_m_id = "james99";
//		
//		
//		ILaunch_activityService service3 = new Launch_activityServiceImpl();
//		
//		
//		List<Launch_activityBean> vsv =service3.getMemberArticles(article_m_id);
//		
//		System.out.println(vsv);
//		

//		OrderService os = new OrderServiceImpl();
//		List<OrderBean> memberOrders = os.getMemberOrders(mb.getMemberId());
//		request.setAttribute("memberOrders", memberOrders);

//		List<Launch_activityBean> launch_activityBeanList =	new ArrayList<Launch_activityBean>();
//					
//		
//		launch_activityBeanList = service3.getMemberArticles(article_m_id);
//		
//		List<String> resultList = launch_activityBeanList.stream().map(Launch_activityBean::getArticle_m_id).collect(Collectors.toList());
//		List<Integer> resultList2 = launch_activityBeanList.stream().map(Launch_activityBean::getF_id).collect(Collectors.toList());
//		
//		
//		System.out.println("vdsv"+resultList);
//		System.out.println("vdsv"+resultList2);

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
