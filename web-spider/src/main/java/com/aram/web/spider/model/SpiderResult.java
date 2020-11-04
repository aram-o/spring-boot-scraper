package com.aram.web.spider.model;

/**
 *
 * @author aram
 */
public class SpiderResult {
    
    private final String url;
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
