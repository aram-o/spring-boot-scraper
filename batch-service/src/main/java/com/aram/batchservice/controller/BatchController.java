package com.aram.batchservice.controller;

import com.aram.batchservice.dto.ComnarconPersonSummaryDTO;
import com.aram.batchservice.mapper.ComnarconPersonMapper;
import com.aram.batchservice.model.ComnarconPerson;
import com.aram.batchservice.service.ComnarconPersonService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aram
 */
@RestController
@RequestMapping("api/v1/")
@Slf4j
public class BatchController {
    
    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    Job processJob;
    
    @Autowired
    private ComnarconPersonService comnarconPersonService;
    
    @Autowired
    private ComnarconPersonMapper comnarconPersonMapper;
    
    /**
     * List all persons.
     * @return
     */
    @RequestMapping(value = "persons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) 
    public List<ComnarconPersonSummaryDTO> list() {
        List<ComnarconPerson> comnarconPersonList = this.comnarconPersonService.getAllPersons();
        
//        Map ComnarconPerson instnce to summary dto.
        List<ComnarconPersonSummaryDTO> comnarconPersonDTOList = comnarconPersonList.stream()
            .map(this.comnarconPersonMapper::toComnarconPersonSummaryDTO).collect(Collectors.toList());
        
        return comnarconPersonDTOList;
    }
    
    /**
     * Invoke batch job.
     * @return 
     * @throws Exception
     */
    @RequestMapping(value = "invokejob", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<?> invoke() throws Exception {
 
        JobParameters jobParameters = new JobParametersBuilder()
            .addLong("time", System.currentTimeMillis())
            .toJobParameters();
        this.jobLauncher.run(this.processJob, jobParameters);
        
//        ToDo return JSON object instead.
        return ResponseEntity.status(HttpStatus.CREATED).body("The batch job invoked successfully.");
 
    }
    
}
