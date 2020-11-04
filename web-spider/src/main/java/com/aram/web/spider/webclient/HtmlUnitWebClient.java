package com.aram.web.spider.webclient;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WaitingRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.WebConnection;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aram
 */
public class HtmlUnitWebClient implements WebClient {

    @Override
    public String getHtml(String url) {
        String html = "";
        try( com.gargoylesoftware.htmlunit.WebClient webClient = this.createWebClient() ) {
            final WebConnection webConnection = webClient.getWebConnection();
            webClient.setWebConnection(new WebConnection() {
                @Override
                public WebResponse getResponse(WebRequest request) throws IOException {
                    return webConnection.getResponse(request);
                }

                @Override
                public void close() throws Exception {
                    webConnection.close();
                }
            });
            HtmlPage page = webClient.getPage(url);
            html = page.asXml();
        } catch (IOException | FailingHttpStatusCodeException ex) {
            Logger.getLogger(HtmlUnitWebClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return html;
    }
    
    /**
     * Returns web client for each crawling URL.
     * @return 
     */
    public com.gargoylesoftware.htmlunit.WebClient createWebClient() {

        final com.gargoylesoftware.htmlunit.WebClient webClient = new com.gargoylesoftware.htmlunit.WebClient(getBrowserVersion());

//        webClient.setJavaScriptErrorListener(null);
//        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
//        webClient.setJavaScriptTimeout(500);
//        webClient.waitForBackgroundJavaScript(490);
        webClient.setRefreshHandler(new WaitingRefreshHandler(100));
        WebClientOptions options = webClient.getOptions();

        options.setRedirectEnabled(true);
        options.setJavaScriptEnabled(false);
        options.setCssEnabled(false);
        options.setUseInsecureSSL(true);

        options.setThrowExceptionOnScriptError(false);
        options.setThrowExceptionOnFailingStatusCode(false);
        options.setPopupBlockerEnabled(false);
        options.setPrintContentOnFailingStatusCode(false);

        return webClient;
    }
    
    /**
     * @return browser version
     */
    public BrowserVersion getBrowserVersion() {
        return BrowserVersion.getDefault();
    }
}
