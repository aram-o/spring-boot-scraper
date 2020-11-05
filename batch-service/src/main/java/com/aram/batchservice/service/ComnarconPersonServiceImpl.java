package com.aram.batchservice.service;

import com.aram.batchservice.dao.ComnarconPersonDAO;
import com.aram.batchservice.model.ComnarconPerson;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aram
 */
@Service
public class ComnarconPersonServiceImpl implements ComnarconPersonService {
    
    @Autowired
    private ComnarconPersonDAO comnarconPersonDAO;

    /**
     * Get all persons.
     * @return
     */
    @Override
    public List<ComnarconPerson> getAllPersons() {
        return this.comnarconPersonDAO.getAllPersons();
    }
    
}
