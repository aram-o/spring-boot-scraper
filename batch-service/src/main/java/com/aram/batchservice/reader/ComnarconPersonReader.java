package com.aram.batchservice.reader;

import com.aram.batchservice.dto.ComnarconPersonDTO;
import com.aram.batchservice.spider.ComnarconLinksSpiderLogic;
import com.aram.batchservice.spider.ComnarconPersonSpiderLogic;
import com.aram.web.spider.WebSpiderImpl;
import com.aram.web.spider.webclient.HtmlUnitWebClient;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * The custom item reader to read person data.
 * @author aram
 */
@Slf4j
public class ComnarconPersonReader implements ItemReader<ComnarconPersonDTO> {

	private List<String> linkList = new ArrayList<>();

	private int count = 0;

    /**
     * Get all links from http://comnarcon.com/?id=26 to read person data.
     * @throws Exception
     */
    public ComnarconPersonReader() throws Exception {
            ComnarconLinksSpiderLogic comnarconLinksSpiderLogic = new ComnarconLinksSpiderLogic();

            WebSpiderImpl webSpider = WebSpiderImpl
                    .builder()
                    .setSpiderLogic(comnarconLinksSpiderLogic)
                    .setWebClient(new HtmlUnitWebClient())
                    .build();

            webSpider.crawl("http://comnarcon.com/?id=26");
            
            this.linkList = comnarconLinksSpiderLogic.getPersonLinkList();
        }
        
    /**
     * Read data for each person and return the DTO instance.
     * @return
     * @throws Exception
     * @throws UnexpectedInputException
     * @throws ParseException
     * @throws NonTransientResourceException
     */
    @Override
	public ComnarconPersonDTO read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
            
            ComnarconPersonSpiderLogic comnarconPersonSpiderLogic = new ComnarconPersonSpiderLogic();

            WebSpiderImpl webSpider = WebSpiderImpl
                    .builder()
                    .setSpiderLogic(comnarconPersonSpiderLogic)
                    .setWebClient(new HtmlUnitWebClient())
                    .build();

            if (count < linkList.size()) {
                webSpider.crawl(linkList.get(count++));
                return comnarconPersonSpiderLogic.getComnarconPersonDTO();
            } else {
                    count = 0;
            }
            return null;
	}
    
}
