package test.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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

public class Snippet {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {  
	      
	    // 屏蔽HtmlUnit等系统 log  
	    java.util.logging.Logger.getLogger("org.apache.http.client").setLevel(Level.OFF);  
	
	    String url = "https://www.cardu.com.tw/news/list.php?nt_pk=6";  
	    System.out.println("Loading page now-----------------------------------------------: "+url);  
	      
	    // HtmlUnit 模拟浏览器
	    WebClient webClient = new WebClient(BrowserVersion.CHROME);  
	    webClient.getOptions().setUseInsecureSSL(true);					//支持https
	    webClient.getOptions().setJavaScriptEnabled(true);              //启用JS解释器，默认为true  
	    webClient.getOptions().setCssEnabled(false);                    //禁用css支持  
	    webClient.getOptions().setThrowExceptionOnScriptError(false);   //js运行错误时，是否抛出异常  
	    webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);  
	    webClient.getOptions().setTimeout(5 * 1000);                   	//设置连接超时时间  
	    
	    HtmlPage page = webClient.getPage(url); 
	    webClient.waitForBackgroundJavaScript(5 * 1000);               // 等待js后台执行30秒  
	    String pageAsXml = page.asXml();
	     
	    // Jsoup解析处理  
	    Document doc = Jsoup.parse(pageAsXml, url);    
	    Elements allJsLinks = doc.select("a[href^='https'][class*='img_div news_list_img']"); 	//AND
//	    Elements allJsLinks = doc.select("a[href^='https'],[class*='img_div news_list_img']");	//OR
	    // 此处省略其他操作
	    
	    for (int i=0;i<allJsLinks.size();i++) {
	    	String titleValue = allJsLinks.get(i).attr("title").toString();		//URL
	    	String urlNews = allJsLinks.get(i).attr("abs:href").toString();		//URI
	    	System.out.println(titleValue);
	    	System.out.println(urlNews);
	    	
////	    	content    	
//	    	HtmlPage pageX = webClient.getPage(url);
//	    	HtmlDivision div = pageX.getHtmlElementById("content");
//	    	String content = div.asText();
//	    	
////	    	image
//	    	
//	    	String img = div.getElementsByTagName("img").toString().substring(28,90); 
//
//	    	System.out.println(img);
//	    	
//            String imageName = img.substring(56);
//            URL urlImg = new URL(img);
//            URLConnection uri = urlImg.openConnection();
//            InputStream is = uri.getInputStream();
//            OutputStream os = new FileOutputStream(new File("data/images/news/"+imageName+".jpg"));
//
//            byte[] buf = new byte[1024];
//            int len = -1;
//
//            while ((len = is.read(buf)) != -1) {
//                os.write(buf, 0, len);
//            }
//	    	
//	    	
//	    	String pageAsXmlT = pageX.asText();
//	    	String pageAsXmlX = pageX.asXml();
//	    	doc = Jsoup.parse(pageAsXmlX, url);
////			doc = Jsoup.parse(pageAsXmlX, url); 
//////			Element jsLinks = doc.getElementsByTag("table").first();
//			System.out.println(content);
	    }  

	    webClient.close();
	} 
}

