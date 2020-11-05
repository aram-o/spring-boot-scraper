package com.aram.batchservice.repositories;

import com.aram.batchservice.model.ComnarconPerson;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author aram
 */
public interface ComnarconPersonRepository extends MongoRepository<ComnarconPerson, String> {
    
}
