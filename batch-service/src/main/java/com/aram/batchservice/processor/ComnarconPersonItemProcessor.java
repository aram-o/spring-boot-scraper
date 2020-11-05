package com.aram.batchservice.processor;

import com.aram.batchservice.dto.ComnarconPersonDTO;
import com.aram.batchservice.model.ComnarconPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

/**
 * The item processor to map ComnarconPersonDTO to ComnarconPerson instance. 
 * @author aram
 */
@Slf4j
public class ComnarconPersonItemProcessor implements ItemProcessor<ComnarconPersonDTO, ComnarconPerson> {
    
    @Override
    public ComnarconPerson process(ComnarconPersonDTO item) throws Exception {

        log.info("processing person data.....{}", item.getTitle());

        ComnarconPerson comnarconPerson = new ComnarconPerson();
        comnarconPerson.setTitle(item.getTitle());
        comnarconPerson.setImgUrl(item.getImgUrl());
        comnarconPerson.setArticleContent(item.getArticleContent());
        return comnarconPerson;
    }
    
}
