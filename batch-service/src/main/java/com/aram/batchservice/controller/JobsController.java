package com.aram.batchservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The jobs controller 
 * 
 * @author aram
 */
@RestController
@RequestMapping("api/v1/jobs")
@Slf4j
public class JobsController {
    
    /**
     * The job launcher
     */
    @Autowired
    private JobLauncher jobLauncher;
 
    /**
     * The job launcher
     */
    @Autowired
    private Job processingJob;
    
    /**
     * Invoke batch job.
     * @return 
     */
    @PostMapping() 
    public ResponseEntity<?> invoke() {
 
        // build job parameters
        JobParameters jobParameters = new JobParametersBuilder()
            .addLong("time", System.currentTimeMillis())
            .toJobParameters();
        
        // launch the job with parameters
        try{ 
            this.jobLauncher.run(this.processingJob, jobParameters);
        }
        catch(JobParametersInvalidException | JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException | JobRestartException e){
            log.error("JobsController: Error while executing a job. Message: " +  e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("The job was not executed.");
        }
        
        // @todo: aram: response with a job object
        return ResponseEntity.status(HttpStatus.CREATED).body("The batch job invoked successfully."); 
    }
}
