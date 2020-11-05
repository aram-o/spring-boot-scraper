package com.aram.batchservice.repositories;

import com.aram.batchservice.model.ComnarconPerson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aram
 */
@Repository
public interface ComnarconPersonRepository extends MongoRepository<ComnarconPerson, String> {
    
}
