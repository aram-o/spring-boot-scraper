package com.aram.batchservice.writer;

import com.aram.batchservice.dao.ComnarconPersonDAO;
import com.aram.batchservice.model.ComnarconPerson;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The custom item writer to save person instance.
 * @author aram
 */
@Slf4j
@Component
public class ComnarconPersonWriter implements ItemWriter<ComnarconPerson> {

    @Autowired
    private ComnarconPersonDAO comnarconPersonDAO;
    
    @Override
    public void write(List<? extends ComnarconPerson> list) throws Exception {
        
        log.info("write persons size {}", list.size());
        this.comnarconPersonDAO.saveAll((Iterable<ComnarconPerson>) list);
        
    }
    
}
