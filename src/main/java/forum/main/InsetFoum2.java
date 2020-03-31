package forum.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import forum.model.Launch_activityBean;
import forum.service.ILaunch_activityService;
import forum.service.impl.Launch_activityServiceImpl;

public class InsetFoum2 {

	public static void main(String[] args) throws IOException, SQLException {

		
//		FoumBean bean2 = new FoumBean();
//		bean2.setFname("旅遊");
//		
//		
//		
//		
//		
//		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//		Transaction tx = null;
//		try {
//			// 打開交易，所有存取資料庫的方法都必須在交易內進行
//			tx = session.beginTransaction();
//			// 執行session物件的save()方法，將beans物件寫入資料庫的Launch_activityBean_Table表格
//			session.save(bean2);
//			// 進行commit
//			tx.commit();
//		} catch (Exception ex) {
//			// 如果發生例外，將交易rollback()
//			if (tx != null)
//				tx.rollback();
//			// 需要印出錯誤訊息
//			ex.printStackTrace();
//		} finally {
//			// 關閉Session物件
//			if (session != null)
//				session.close();
//		}
//		// 當所有的資料庫存取的指令都做完後，關閉SessionFactory物件
//		HibernateUtils.getSessionFactory().close();
		
		ILaunch_activityService la = new Launch_activityServiceImpl();
		List list = la.getMemberArticles("mickey");
		Launch_activityBean l = (Launch_activityBean)list.get(0);
		System.out.println("greg="+l.getFoumBean().getFname());
//		System.out.println(l.toString());

	}

}
