package init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
			stmt.executeUpdate(DBService.getDropMembers());
			System.out.println("會員(members)表格刪除成功");
			stmt.executeUpdate(DBService.getCreateMembers());
			System.out.println("會員(members)表格產生成功");
			// 2. cards表格
			stmt.executeUpdate(DBService.getDropCards());
			System.out.println("信用卡 cards表格刪除成功");
			stmt.executeUpdate(DBService.getCreateCards());
			System.out.println("信用卡 cards表格產生成功");
			// 3. product表格
			stmt.executeUpdate(DBService.getDropProduct());
			System.out.println("商品檔 product表格刪除成功");
			stmt.executeUpdate(DBService.getCreateProduct());
			System.out.println("商品檔 product表格產生成功");
			// 4.
			stmt.executeUpdate(DBService.getDropOrder());
			System.out.println("訂單 order表格刪除成功");
			stmt.executeUpdate(DBService.getCreateOrder());
			System.out.println("訂單 order表格產生成功");
			// 5.
			stmt.executeUpdate(DBService.getDropOrderItem());
			System.out.println("訂單明細 orderitem表格刪除成功");
			stmt.executeUpdate(DBService.getCreateOrderItem());
			System.out.println("訂單明細 orderitem表格產生成功");
			// 6.holdcards表格
			stmt.executeUpdate(DBService.getDropHoldcards());
			System.out.println("會員的持卡 holdcards表格刪除成功");
			stmt.executeUpdate(DBService.getCreateHoldcards());
			System.out.println("會員的持卡 holdcards表格產生成功");
			// 7.forum表格
			stmt.executeUpdate(DBService.getDropForum());
			System.out.println("討論版 forum表格刪除成功");
			stmt.executeUpdate(DBService.getCreateForum());
			System.out.println("討論版 forum表格產生成功");
			// 8.
			stmt.executeUpdate(DBService.getDropLaunchActivity());
			System.out.println("主題文章 launch_activity表格刪除成功");
			stmt.executeUpdate(DBService.getCreateLlaunchActivity());
			System.out.println("主題文章 launch_activity表格產生成功");
			// 9.
			stmt.executeUpdate(DBService.getDropResponser());
			System.out.println("回應文章  responser 表格刪除成功");
			stmt.executeUpdate(DBService.getCreateResponser());
			System.out.println("回應文章  responser表格產生成功");
			

			
		}
		 catch (SQLException e) {
			System.err.println("新建表格時發生SQL例外: " + e.getMessage());
			e.printStackTrace();
		} 

	}

	
	
	
}