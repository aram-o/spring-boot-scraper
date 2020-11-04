package com.aram.web.spider.logic;

import com.aram.web.spider.webclient.WebClient;

/**
 *
 * @author aram
 */
public interface SpiderLogic {
    
    void apply(String url, WebClient webClient);
    
}
