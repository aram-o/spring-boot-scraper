package com.aram.batchservice;

import com.aram.batchservice.spider.ComnarconLinksSpiderLogic;
import com.aram.batchservice.spider.ComnarconPersonSpiderLogic;
import com.aram.web.spider.WebSpiderImpl;
import com.aram.web.spider.webclient.HtmlUnitWebClient;
import org.junit.jupiter.api.Test;

class BatchServiceApplicationTests {

    @Test
    void contextLoads() throws Exception {

        ComnarconLinksSpiderLogic comnarconLinksSpiderLogic = new ComnarconLinksSpiderLogic();

        WebSpiderImpl webSpider = WebSpiderImpl
                .builder()
                .setSpiderLogic(comnarconLinksSpiderLogic)
                .setWebClient(new HtmlUnitWebClient())
                .build();

        webSpider.crawl("http://comnarcon.com/?id=26");

        System.err.println("ComnarconLinksSpiderLogic links size " + comnarconLinksSpiderLogic.getPersonLinkList().size());

        ComnarconPersonSpiderLogic comnarconPersonSpiderLogic = new ComnarconPersonSpiderLogic();

        webSpider = WebSpiderImpl
                .builder()
                .setSpiderLogic(comnarconPersonSpiderLogic)
                .setWebClient(new HtmlUnitWebClient())
                .build();

        webSpider.crawl(comnarconLinksSpiderLogic.getPersonLinkList().get(0));
        System.err.println("imgUrl" + comnarconPersonSpiderLogic.getComnarconPersonDTO().getImgUrl());
        System.err.println("title-" + comnarconPersonSpiderLogic.getComnarconPersonDTO().getTitle());
    }

}
