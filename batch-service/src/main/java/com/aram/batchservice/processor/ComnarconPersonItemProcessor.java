package com.aram.batchservice.processor;

import com.aram.batchservice.dto.ComnarconPersonDTO;
import com.aram.batchservice.mapper.PersonMapper;
import com.aram.batchservice.model.ComnarconPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The item processor to map ComnarconPersonDTO to ComnarconPerson instance. 
 * @author aram
 */
@Slf4j
@Component
public class ComnarconPersonItemProcessor implements ItemProcessor<ComnarconPersonDTO, ComnarconPerson> {
    
    @Autowired
    private PersonMapper comnarconPersonMapper;
    
    @Override
    public ComnarconPerson process(ComnarconPersonDTO item) throws Exception {

        log.info("processing person data.....{}", item.getTitle());

        return this.comnarconPersonMapper.toComnarconPerson(item);
    }
    
}
