package com.aram.web.spider.model;

/**
 * The spider result which will contain current and next urls.
 * @author aram
 */
public class SpiderResult {
    
//    The current url.
    private final String url;
//    The next url.
    private final String nextUrl;

    public SpiderResult(String url, String nextUrl) {
        this.url = url;
        this.nextUrl = nextUrl;
    }

    public String getUrl() {
        return this.url;
    }

    public String getNextUrl() {
        return this.nextUrl;
    }
    
    
}
