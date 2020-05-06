

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import forum.model.FoumBean;
import forum.service.IFoumService;
import forum.service.impl.FoumServiceImpl;
import init.utils.DBService;

//import _00_init.util.DBService;
//import _00_init.util.GlobalService;
//import _01_register.model.MemberBean;
//import _03_listBooks.model.BookBean;
//import _03_listBooks.model.CompanyBean;
// 本程式建立範例程式所需要的表格與部分表格的初始資料
// 範例程式所需要的表格如下：
// 
public class EDMTableReset {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {

		String line = "";

		int count = 0;
		
		try (
			Connection con = DriverManager.getConnection(
						DBService.getDbUrl(), 
						DBService.getUser(),
						DBService.getPassword()); 
			Statement stmt = con.createStatement();
		) {
			// 1. members表格
			stmt.executeUpdate(DBService.getDropResponser());
			System.out.println("回應文章  responser 表格刪除成功");
			stmt.executeUpdate(DBService.getDropOrderItem());
			System.out.println("訂單明細 orderitem表格刪除成功");
			stmt.executeUpdate(DBService.getDropProduct());
			System.out.println("商品檔 product表格刪除成功");
			stmt.executeUpdate(DBService.getDropLaunchActivity());
			System.out.println("主題文章 launch_activity表格刪除成功");
			stmt.executeUpdate(DBService.getDropForum());
			System.out.println("討論版 forum表格刪除成功");
			stmt.executeUpdate(DBService.getDropHoldcards());
			System.out.println("會員的持卡 holdcards表格刪除成功");
			stmt.executeUpdate(DBService.getDropOrder());
			System.out.println("訂單 order表格刪除成功");
			stmt.executeUpdate(DBService.getDropPair());
			System.out.println("pair表格刪除成功");
			stmt.executeUpdate(DBService.getDropMembers());
			System.out.println("會員(members)表格刪除成功");		
			stmt.executeUpdate(DBService.getDropCards());
			System.out.println("信用卡 cards表格刪除成功");
			// 2. cards表格
			stmt.executeUpdate(DBService.getCreateMembers());
			System.out.println("會員(members)表格產生成功");
			stmt.executeUpdate(DBService.getCreateCards());
			System.out.println("信用卡 cards表格產生成功");
			stmt.executeUpdate(DBService.getCreateProduct());
			System.out.println("商品檔 product表格產生成功");
			stmt.executeUpdate(DBService.getCreateOrder());
			System.out.println("訂單 order表格產生成功");
			stmt.executeUpdate(DBService.getCreateOrderItem());
			System.out.println("訂單明細 orderitem表格產生成功");
			stmt.executeUpdate(DBService.getCreateHoldcards());
			System.out.println("會員的持卡 holdcards表格產生成功");
			stmt.executeUpdate(DBService.getCreateForum());
			System.out.println("討論版 forum表格產生成功");
			stmt.executeUpdate(DBService.getCreateLlaunchActivity());
			System.out.println("主題文章 launch_activity表格產生成功");
			stmt.executeUpdate(DBService.getCreateResponser());
			System.out.println("回應文章  responser表格產生成功");
			stmt.executeUpdate(DBService.getCreatePair());
			System.out.println("回應文章  pair表格產生成功");
			// 5.
			// 4.
			// 3. product表格
			// 6.holdcards表格
			// 7.forum表格
			// 8.

			
			
			String [] n= {"旅遊","魯蛇卡","加油回饋","無限卡","電影","購物","現金回饋","宗教","公益","鈦金or御璽卡","運動"};
			for(String fname:n) {  // = for(int i =0; i<n.length;i++)
			IFoumService service = new FoumServiceImpl();
			FoumBean forum = new FoumBean(null, fname, null);
			service.insertFoum(forum);		
			}	
			
			
			
			
			
			
			
			
			
		}
		 catch (SQLException e) {
			System.err.println("新建表格時發生SQL例外: " + e.getMessage());
			e.printStackTrace();
		} 

	}

	
	
	
}