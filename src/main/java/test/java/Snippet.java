package test.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Snippet 
  {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException 
	{  
	      
	    // 屏蔽HtmlUnit等系统 log  
	    java.util.logging.Logger.getLogger("org.apache.http.client").setLevel(Level.OFF);  
	
	    String url = "https://www.money101.com.tw/%E4%BF%A1%E7%94%A8%E5%8D%A1/%E5%85%A8%E9%83%A8";  //要抓取的網址
	    System.out.println("開始抓優惠資訊");  
	      
	    // HtmlUnit 模拟浏览器
	    WebClient webClient = new WebClient(BrowserVersion.CHROME);  
	    webClient.getOptions().setUseInsecureSSL(true);					//支持https
	    webClient.getOptions().setJavaScriptEnabled(true);              //启用JS解释器，默认为true  
	    webClient.getOptions().setCssEnabled(false);                    //禁用css支持  
	    webClient.getOptions().setThrowExceptionOnScriptError(false);   //js运行错误时，是否抛出异常  
	    webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);  
	    webClient.getOptions().setTimeout(5 * 1000);                   	//设置连接超时时间  
	    
	    HtmlPage page = webClient.getPage(url); 
	    webClient.waitForBackgroundJavaScript(5 * 1000);               // 執行程式之後等待js運作去抓取網站的時間  
	    String pageAsXml = page.asXml();
	     
	    // Jsoup解析处理  
	    Document doc = Jsoup.parse(pageAsXml);    
	    Elements allJsLinks =doc.select("div[class*='card product-pane']").select("a[href^='/%E4%BF']"); 	//AND 取得標題與分頁連結
  //    Elements pcontext =doc.select("p[class*='phone_hidden']"); //這是取得P內容
  //    Elements datatext =doc.select("span[class*='mb-1']"); //這是取得新聞日期內容
  //    Elements allJsLinks = doc.select("a[href^='https'],[class*='img_div news_list_img']");	//OR
             
	   for (int i=0;i<allJsLinks.size();i++) 
	   {
	        //String titleValue = allJsLinks.get(i).attr("title").toString();		//URL
	        String urlNews = allJsLinks.get(i).text();		//URI	
	      //String pstr = pcontext.get(i).text();
	    //	String datastr = datatext.get(i).text();
	    
	
	       System.out.println(urlNews);
	    	
	    	
	    	
	    	//從連結網站再往裡面找一次要的資料
	      /*  page = webClient.getPage(urlNews);
	        webClient.waitForBackgroundJavaScript(5 * 1000);
	        pageAsXml = page.asXml();
	        Document docNews = Jsoup.parse(pageAsXml);
	        Elements alltext =docNews.select("[class*='detail_title']").select("h2"); //文章標題
	        Elements acttime =docNews.select("[class*='detail_title']").select("p");//活動時間
	        Elements contextarea =docNews.select("[class*='detail_content']").select("p");//文章內容  
	        Elements imgarea =docNews.select("[class*='con_img']").select("img");//因為要找圖片所以要爬兩層
	      
	    
	        String textvalue=alltext.text();//標題
	        String acttext=acttime.text();//活動時間
	        String contextvalue=contextarea.get(3).text();//文章內容
	        String imgpicture = imgarea.attr("src").toString();//找出img src連結	
	         
	        
	    	FileWriter fw2=new FileWriter("c:/information/page/日期為"+dateNowStr+"時分頁優惠資訊.txt",true);//產生的路徑放置位置,true為不覆蓋檔案
		    BufferedWriter bufferOut2 = new BufferedWriter(fw2,20);//20為一個緩衝區的字元可以自己設定
		 
		    bufferOut2.write(textvalue);//標題
		    bufferOut2.newLine();//換行
		    bufferOut2.write(acttext);//活動時間
		    bufferOut2.newLine();//換行
		    bufferOut2.write(contextvalue);//文章內容
		    bufferOut2.newLine();//換行
		    bufferOut2.flush();//緩衝區內容立刻寫入檔案
            fw2.close();
            System.out.println("第"+(i+1)+"份分資料產生"); */
            
     

            
	    }
            System.out.println("全部資料皆以輸出"); 
	 
     	}
	  }
	 	 

	
	

	
	









