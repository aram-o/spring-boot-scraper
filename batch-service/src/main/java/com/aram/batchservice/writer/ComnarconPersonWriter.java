package com.aram.batchservice.writer;

import com.aram.batchservice.model.ComnarconPerson;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

/**
 * The custom item writer to save person instance.
 * @author aram
 */
@Slf4j
public class ComnarconPersonWriter implements ItemWriter<ComnarconPerson> {

    @Override
    public void write(List<? extends ComnarconPerson> list) throws Exception {
        for( ComnarconPerson person : list ) {
            log.info("write person to db {}", person.getTitle());
        }
    }
    
}
