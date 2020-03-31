package forum.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forum.model.FoumBean;
import forum.model.Launch_activityBean;
import forum.service.ILaunch_activityService;
import forum.service.impl.Launch_activityServiceImpl;
import member.model.MemberBean;

@WebServlet("/forum/Launch_activityServlet")
public class Launch_activityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();

		if (session == null) {
			// 請使用者登入
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "member_login.jsp"));
			return;
		}

		// 沒有登入會員 前往首頁
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.html");
			return;
		}
		// 定義存放錯誤訊息的Map物件
		Map<String, String> errorMesgs = new HashMap<String, String>();
		// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
		request.setAttribute("ErrorMsgKey", errorMesgs);
//---------------------------------------------------------------------------------------
		// 1. 讀取使用者輸入資料

		String article_title = request.getParameter("article_title");//標題
		String article_content = request.getParameter("article_content");//內容	
		String subject = request.getParameter("subject");//主題
		String location = request.getParameter("location");//地點
		String postTimeStr = request.getParameter("postTime");//活動開始時間	
		String endTimeStr = request.getParameter("endTime");//活動結束時間
		String f_id = request.getParameter("f_id");//版名阿
		String popularity = request.getParameter("popularity");//人氣
		FoumBean fob = new FoumBean();
		fob.getF_id();
//---------------------------------------------------------------------------------------
		// 2-1. 進行必要的資料轉換(時間)

		Date post_Time = null;
		if (postTimeStr == null || postTimeStr.trim().length() == 0) {
			errorMesgs.put("postTimeError", "活動開始的時間需要整數");
		} else {
			try {
				DateFormat df = DateFormat.getDateInstance();
				post_Time = df.parse(postTimeStr);
			} catch (Exception e) {
				errorMesgs.put("postTime", "開始的時間格式錯誤，應該為yyyy/MM/dd/HH");
			}
		}

		Date endTime = null;

		if (endTimeStr == null || endTimeStr.trim().length() == 0) {
			errorMesgs.put("endTimeError", "結束時間至少一天");
		} else {
			try {
				DateFormat df = DateFormat.getDateInstance();
				endTime = df.parse(endTimeStr);
			} catch (Exception e) {
				errorMesgs.put("endTime", "結束時間格式錯誤，應該為yyyy/MM/dd/HH");
			}
		}

		java.sql.Timestamp updateTime = new java.sql.Timestamp(System.currentTimeMillis());

		String article_Id = request.getParameter("article_Id");
		int Iarticle_Id = 0;
		if (article_Id != null && article_Id.trim().length() > 0) {
			try {
				Iarticle_Id = Integer.parseInt(article_Id.trim());
			} catch (NumberFormatException e) {
				errorMesgs.put("文章編號為整數", article_Id);
			}
		}
		// 2-2. 進行必要的資料轉換(Integer)
		int If_id = 0;
		if (f_id != null && f_id.trim().length() > 0) {
			try {
				If_id = Integer.parseInt(f_id.trim());
			} catch (NumberFormatException e) {
				errorMesgs.put("版名編號為整數", f_id);
			}
		}

		int Ipopularity = 0;
		if (popularity != null && popularity.trim().length() > 0) {
			try {
				Ipopularity = Integer.parseInt(popularity.trim());
			} catch (NumberFormatException e) {
				errorMesgs.put("人氣為整數", popularity);
			}
		}
//---------------------------------------------------------------------------------------
		// 3. 檢查使用者輸入資料

		// 如果 article_title 欄位為空白，或少於10個字，放一個錯誤訊息到 errorMsgMap 之內
		if (article_title == null | article_title.trim().length() <= 11) {
			errorMesgs.put("TitleError", "標題不能少於10個字");
		}
		// 如果 article_content 欄位為空白，或少於100個字，放一個錯誤訊息到 errorMsgMap 之內
		if (article_content == null | article_content.trim().length() <= 101) {
			errorMesgs.put("ContentError", "內容不能少於100個字");
		}
		if(subject.isEmpty()) {
			subject=null;
		}

		// 如果 errorMsgMap 是空的，表示都對了，交棒給forum.jsp
		if (errorMesgs.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("forum.jsp");
			rd.forward(request, response);
			return;
		}
//---------------------------------------------------------------------------------------
		// 4. 進行 Business Logic 運算(調用方法)// Launch_activityBean 扮演封裝輸入資料的角色

//		Launch_activityBean laBean = new Launch_activityBean(Iarticle_Id, mb.getM_id(), If_id, article_title,
//		article_content, subject, location, post_Time, updateTime, endTime, Ipopularity);
//		
//		
//		ILaunch_activityService labservice = new Launch_activityServiceImpl();
//		
//		try {
//			labservice.insertArticle(laBean);
//			
//			labservice.updateArticle(laBean);
//			
//			labservice.DeleteArticle(laBean);
//
//			request.setAttribute("Launch_activityBean", laBean);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Launch_activityServlet.jsp");
		rd.forward(request, response);
		return;

	}

}
