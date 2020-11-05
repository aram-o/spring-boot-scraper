package com.aram.web.spider;

import com.aram.web.spider.logic.SpiderLogic;
import com.aram.web.spider.webclient.WebClient;

/**
 * The web spider to build all logic needed for crawling.
 * @author aram
 */
public class WebSpiderImpl implements WebSpider {
    
//    The spider logic to be used.
    private final SpiderLogic spiderLogic;
//    The web client to be used.
    private final WebClient webClient;

    private WebSpiderImpl(SpiderLogic spiderLogic, WebClient webClient) {
        this.spiderLogic = spiderLogic;
        this.webClient = webClient;
    }

    @Override
    public void crawl(String url) throws Exception {
        this.spiderLogic.apply(url, this.webClient);
    }
    
    /**
     * The builder.
     * @return WebSpiderBuilder
     */
    public static WebSpiderBuilder builder() {
        return new WebSpiderBuilder();
    }
    
    /**
     *  The WebSpiderBuilder
     */
    public static class WebSpiderBuilder {

        private SpiderLogic spiderLogic;
        private WebClient webClient;

        public WebSpiderBuilder setSpiderLogic(SpiderLogic spiderLogic) {
            this.spiderLogic = spiderLogic;
            return this;
        }
        
        public WebSpiderBuilder setWebClient(WebClient webClient) {
            this.webClient = webClient;
            return this;
        }

        public WebSpiderImpl build() {
            this.validateState();
            return new WebSpiderImpl(this.spiderLogic, this.webClient);
        }

        private void validateState() {
            if(this.spiderLogic == null) {
                throw new IllegalStateException("spiderLogic is null");
            }
        }
    }
}
