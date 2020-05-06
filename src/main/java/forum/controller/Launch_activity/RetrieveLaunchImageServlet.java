package forum.controller.Launch_activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forum.model.Launch_activityBean;
import forum.service.ILaunch_activityService;
import forum.service.impl.Launch_activityServiceImpl;

@WebServlet("/forum/RetrieveLaunchImageServlet")
public class RetrieveLaunchImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			OutputStream os = null;
			InputStream is = null;
//			String mimeType = null;
			Blob blob = null;
		
			try {
				// 讀取瀏覽器傳送來的主鍵
				String id = request.getParameter("article_IdStr");
				// 讀取瀏覽器傳送來的type，以分辨要處理哪個表格
				ILaunch_activityService launch_activityService = new Launch_activityServiceImpl();
				
				int article_Id = 0;
				try {
					article_Id = Integer.parseInt(id);
				} catch(NumberFormatException ex) {
					ex.printStackTrace();
				}
				Launch_activityBean bean = launch_activityService.getArticle_Id(article_Id);
				if (bean != null) {
					blob = bean.getArticleimage();
					if (blob != null) {
						is = blob.getBinaryStream();
					}
					
				}
				os = response.getOutputStream();	
//				if (is == null) {
//					fileName = "NoImage.png" ; 
//					is = getServletContext().getResourceAsStream(
//							"/images/" + fileName);
//				}
				
				
				// 由圖片檔的檔名來得到檔案的MIME型態
//				mimeType = getServletContext().getMimeType(fileName);
				// 設定輸出資料的MIME型態
//				response.setContentType(mimeType);
				// 取得能寫出非文字資料的OutputStream物件
				// 由InputStream讀取位元組，然後由OutputStream寫出
				
				int len = 0;
				byte[] bytes = new byte[8192];
				while ((len = is.read(bytes)) != -1) {
					os.write(bytes, 0, len);
				}
			} catch(SQLException ex) {
				ex.printStackTrace();
			} finally{
				if (is != null) is.close();
				if (os != null) os.close();
				
			}
			
			
			
		}
		
		
	}

	


