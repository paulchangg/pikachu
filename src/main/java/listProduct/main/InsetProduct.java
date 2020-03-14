package listProduct.main;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import init.GlobalService;
import init.HibernateUtils;
import listProduct.model.ProductBean;

public class InsetProduct {

	public static void main(String[] args) throws IOException, SQLException {
		ProductBean beans = new ProductBean();
		beans.setP_name("Apple iPhone 11 (256G)-綠色");
		beans.setP_desc("6.1 吋 Liquid Retina HD LCD 顯示器");
		beans.setPrice(30400.0);
		beans.setStock(3);
		Blob p_img = GlobalService.fileToBlob("D:\\02.jpg");
		beans.setP_img(p_img);
		beans.setP_bns(50000);
		
		
		
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			// 打開交易，所有存取資料庫的方法都必須在交易內進行
			tx = session.beginTransaction();
			// 執行session物件的save()方法，將emp物件寫入資料庫的Employee_Table表格
			session.persist(beans);
			// 進行commit
			tx.commit();
		} catch (Exception ex) {
			// 如果發生例外，將交易rollback()
			if (tx != null)
				tx.rollback();
			// 需要印出錯誤訊息
			ex.printStackTrace();
		} finally {
			// 關閉Session物件
			if (session != null)
				session.close();
		}
		// 當所有的資料庫存取的指令都做完後，關閉SessionFactory物件
		HibernateUtils.getSessionFactory().close();
//		private Integer p_id;
//		private String p_name;
//		private String p_desc;
//		private Double price;
//		private Integer stock;
//		private Blob p_img;
//		private Integer p_bns;
	}

}
