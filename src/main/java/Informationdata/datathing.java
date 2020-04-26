package Informationdata;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class datathing {

    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        
        // 屏蔽HtmlUnit等系统 log
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log","org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http.client").setLevel(Level.OFF);
        
        String url = "https://www.cardu.com.tw/news/list.php?nt_pk=6";
        System.out.println("Loading page now-----------------------------------------------: "+url);
        
        /* HtmlUnit 模拟浏览器 */
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);              // 启用JS解释器，默认为true  
        webClient.getOptions().setCssEnabled(false);                    // 禁用css支持  
        webClient.getOptions().setThrowExceptionOnScriptError(false);   // js运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(10 * 1000);                   // 设置连接超时时间
        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(30 * 1000);               // 等待js后台执行30秒

        String pageAsXml = page.asXml();
        
        /* Jsoup解析处理 */
        // Document doc = Jsoup.parse(pageAsXml, "https://bluetata.com/");
        Document doc = Jsoup.parse(pageAsXml);  
        //Elements pngs = doc.select("img[src$=.png]");                   // 获取所有图片元素集
        
        Elements eles = doc.select("td.title_1");
        // 其他操作
        System.out.println(eles.toString());
    }
}