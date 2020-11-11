package com.aram.batchservice.controller;

import com.aram.batchservice.mapper.PersonMapper;
import com.aram.batchservice.model.ComnarconPerson;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aram.batchservice.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The persons controller
 * 
 * @author aram
 */
@RestController
@RequestMapping("api/v1/persons")
@Slf4j
public class PersonsController {
    
    /**
     * The Comnarcon Person Service
     */
    @Autowired
    private PersonService personService;
    
    /**
     * The persons DTO mapper
     */
    @Autowired
    private PersonMapper personMapper;
    
    /**
     * Get the list of persons
     * 
     * @return Returns persons list
     */
    @GetMapping() 
    public ResponseEntity<?> getAll() {
        
        // get all persons
        List<ComnarconPerson> allPersons = this.personService.getAllPersons();
        
        // map to summary dto
        return ResponseEntity.ok(allPersons
                .stream()
                .map(this.personMapper::toSummary)
                .collect(Collectors.toList()));
    }
}
