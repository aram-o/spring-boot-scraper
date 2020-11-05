package com.aram.batchservice.dao;

import com.aram.batchservice.model.ComnarconPerson;
import com.aram.batchservice.repositories.ComnarconPersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author aram
 */
@Component
public class ComnarconPersonDAOImpl implements ComnarconPersonDAO {
    
    @Autowired
    private ComnarconPersonRepository comnarconPersonRepository;

    /**
     * Find all persons from DB.
     * @return
     */
    @Override
    public List<ComnarconPerson> getAllPersons() {
        return this.comnarconPersonRepository.findAll();
    }
    
    @Override
    public void saveAll(Iterable<ComnarconPerson> list) {
        this.comnarconPersonRepository.saveAll(list);
    }
    
}
