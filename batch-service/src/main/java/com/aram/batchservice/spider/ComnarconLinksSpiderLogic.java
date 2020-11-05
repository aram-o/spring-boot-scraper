package com.aram.batchservice.spider;

import com.aram.web.spider.logic.SpiderLogic;
import com.aram.web.spider.model.SpiderResult;
import com.aram.web.spider.utils.UrlUtils;
import com.aram.web.spider.webclient.WebClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * The spider logic to get all links.
 * @author aram
 */
public class ComnarconLinksSpiderLogic implements SpiderLogic {
    
    private final List<String> personLinkList = new ArrayList<>();

    public List<String> getPersonLinkList() {
        return this.personLinkList;
    }

    @Override
    public void apply(String url, WebClient webClinet) {
//        Do not continue if url is null.
        if( url == null ) {
            return;
        }
        
        SpiderResult spiderResult;
        try {
            String html = webClinet.getHtml(url);
//            Do not continue if html is null or empty.
            if( html == null || html.equals("") ) {
                return;
            }
            spiderResult = this.paginate(url, html);
//            Do not continue if the spiderResult is null.
            if( spiderResult == null ) {
                return;
            }
            
            this.apply(spiderResult.getNextUrl(), webClinet);
        } catch (IOException ex) {
            Logger.getLogger(ComnarconLinksSpiderLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private SpiderResult paginate(String url, String html) throws IOException {
//            Do not continue if the html is null or empty.
        if( html == null || html.equals("") ) {
            return null;
        }
        
        Document doc = Jsoup.parse(html);
        
        this.addPersonLinks(html);
        
        Element link = doc.selectFirst("div.pagination>li>a.active[href]");
//            Do not continue if the link is null or next sibling is absent.
        if( link == null
            || link.parent() == null 
            || link.parent().nextElementSibling() == null 
            || link.parent().nextElementSibling().childNodeSize() < 1         
        ) {
            return null;
        }
        
        String nextLink = link.parent().nextElementSibling().child(0).attr("href");
//            Do not continue if nextLink is null.
        if( nextLink == null ) {
            return null;
        }
        
        String domain = UrlUtils.getDomainNameNoException(url);
//            Do not continue if domain is null.
        if( domain == null ) {
            return null;
        }
        
        return new SpiderResult(url, UrlUtils.makeFullUrl(domain, url));
    }
    
    private Elements addPersonLinks(String html) {
//            Do not continue if the html is null or empty.
        if( html == null || html.equals("") ) {
            return null;
        }
        
        Document doc = Jsoup.parse(html);
        
        Elements personLinks = doc.select("div.persona-title > h3 > a[href]");
        
        if( personLinks != null ) {
            for( Element personLink : personLinks ) {
                if( personLink != null && personLink.attr("href") != null ) {
                    this.personLinkList.add(personLink.attr("href"));
                }
            }
        }
        
        return personLinks;
    }
    
    
}
