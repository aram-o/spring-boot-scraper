package com.aram.batchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * The Batch Service Application
 * 
 * @author aram
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BatchServiceApplication {

    /**
     * The entry point of batch service application
     * 
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(BatchServiceApplication.class, args);
    }
}
