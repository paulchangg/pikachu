package card.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import card.model.CardBean;
import card.service.CardService;
import card.service.impl.CardServiceImpl;

@WebServlet("/cards/cradeitCb")
public class CbPageCardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	int pageNo = 1;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
				String pageNoStr = request.getParameter("pageNo");
		
//		HttpSession session = request.getSession(false);
//		
//		if (pageNoStr == null) {  
//			pageNo = 1;
//			Cookie[] cookies = request.getCookies();
//			if (cookies != null) {
//				for (Cookie c : cookies) {
//					if (c.getName().equals(memberId + "pageNo")) {
//						try {
//							pageNo = Integer.parseInt(c.getValue().trim());
//						} catch (NumberFormatException e) {
//							;
//						}
//						break;
//					}
//				}
//			}
//		} else {
//			try {
//				pageNo = Integer.parseInt(pageNoStr.trim());
//			} catch (NumberFormatException e) {
//				pageNo = 1;
//			}
//		}
	    int recordsPerPage = 8;
		CardService service = new CardServiceImpl();
		Map<Integer, CardBean> CardMap = service.getCbPageCards(pageNo);
		int cardCounts = CardMap.size();
		
		int totalPages = (int)(Math.ceil( cardCounts / (double)recordsPerPage));

		session.setAttribute("pageNo", String.valueOf(pageNo));
		request.setAttribute("totalPages", totalPages);
		session.setAttribute("products_DPP", CardMap);
		
//		// 使用Cookie來儲存目前讀取的網頁編號，Cookie的名稱為memberId + "pageNo"
//		// -----------------------
//		Cookie pnCookie = new Cookie(memberId + "pageNo", String.valueOf(pageNo));
//	    // 設定Cookie的存活期為30天
//		pnCookie.setMaxAge(30 * 24 * 60 * 60);
//	    // 設定Cookie的路徑為 Context Path		
//		pnCookie.setPath(request.getContextPath());
//		// 將Cookie加入回應物件內
//		response.addCookie(pnCookie);
//		// -----------------------
		
		RequestDispatcher rd = request.getRequestDispatcher("/cards/cradeitsearch_page.jsp");
		rd.forward(request, response);
		return;
		
		
	}

}
