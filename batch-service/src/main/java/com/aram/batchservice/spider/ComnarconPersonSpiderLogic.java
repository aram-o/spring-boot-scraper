package com.aram.batchservice.spider;

import com.aram.batchservice.dto.ComnarconPersonDTO;
import com.aram.web.spider.logic.SpiderLogic;
import com.aram.web.spider.utils.UrlUtils;
import com.aram.web.spider.webclient.WebClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * The spider logic to parse person data.
 * @author aram
 */
public class ComnarconPersonSpiderLogic implements SpiderLogic {
    
    private ComnarconPersonDTO comnarconPersonDTO;

    public ComnarconPersonDTO getComnarconPersonDTO() {
        return comnarconPersonDTO;
    }
    
    @Override
    public void apply(String url, WebClient webClinet) {
//        Do not continue if url is null.
        if( url == null ) {
            return;
        }
        
        try {
            String html = webClinet.getHtml(url);
//            Do not continue if html is null or empty.
            if( html == null || html.equals("") ) {
                return;
            }
            this.parse(url, html);
            
        } catch (IOException ex) {
            Logger.getLogger(ComnarconPersonSpiderLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String parse(String url, String html) throws IOException {
//            Do not continue if the html is null or empty.
        if( html == null || html.equals("") ) {
            return null;
        }
        
        Document doc = Jsoup.parse(html);
        
        Element articleText = doc.selectFirst("div.article_text");
        if( articleText == null ) {
            return null;
        }
        
        this.comnarconPersonDTO = new ComnarconPersonDTO();
        
        this.comnarconPersonDTO.setArticleContent(articleText.html());
        
        Elements imgs = articleText.select("img");
        if( imgs != null && !imgs.isEmpty() ) {
            Element img = imgs.get(1);
            String domain = UrlUtils.getDomainNameNoException(url);
            this.comnarconPersonDTO.setImgUrl(UrlUtils.makeFullUrl(domain, img.attr("src")));

            if( img.parent() != null
                && img.parent().previousElementSibling() != null 
                && img.parent().previousElementSibling().childNodeSize() > 0 
            ) {
                this.comnarconPersonDTO.setTitle(img.parent().previousElementSibling().child(0).ownText());
            }
            
        }
        
        return articleText.html();
    }
    
}
