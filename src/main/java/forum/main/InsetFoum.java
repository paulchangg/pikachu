package forum.main;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import init.GlobalService;
import init.HibernateUtils;
import listProduct.model.ProductBean;

public class InsetFoum {

	public static void main(String[] args) throws IOException, SQLException {
		ProductBean beans = new ProductBean();
		beans.setP_name("Apple iPhone 11 (256G)-蝬");
		beans.setP_desc("6.1 ��� Liquid Retina HD LCD 憿舐內�");
		beans.setPrice(30400.0);
		beans.setStock(3);
		Blob p_img = GlobalService.fileToBlob("D:\\�憓��冗\\02.jpg");
		beans.setP_img(p_img);
		beans.setP_bns(50000);
		beans.setP_img_name("02.jpg");;
		
		
		
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			// ���漱����������澈��瘜敹�鈭斗��脰��
			tx = session.beginTransaction();
			// �銵ession�隞嗥�ave()�瘜��mp�隞嗅神�鞈�澈��mployee_Table銵冽
			session.persist(beans);
			// �脰�ommit
			tx.commit();
		} catch (Exception ex) {
			// 憒�������漱��ollback()
			if (tx != null)
				tx.rollback();
			// ��閬��隤方�
			ex.printStackTrace();
		} finally {
			// ���ession�隞�
			if (session != null)
				session.close();
		}
		// ��������澈摮���誘��������essionFactory�隞�
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
