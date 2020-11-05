package com.aram.batchservice.dao;

import com.aram.batchservice.model.ComnarconPerson;
import java.util.List;

/**
 *
 * @author aram
 */
public interface ComnarconPersonDAO {
    
    List<ComnarconPerson> getAllPersons();
    void saveAll(Iterable<ComnarconPerson> comnarconPerson);
    
}
