package com.aram.batchservice.config;

import com.aram.batchservice.dto.ComnarconPersonDTO;
import com.aram.batchservice.listener.JobCompletionListener;
import com.aram.batchservice.model.ComnarconPerson;
import com.aram.batchservice.processor.ComnarconPersonItemProcessor;
import com.aram.batchservice.reader.ComnarconPersonReader;
import com.aram.batchservice.writer.ComnarconPersonWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The batch configuration class.
 * @author aram
 */
@Configuration
@EnableBatchProcessing
public class ComnarconPersonConfiguration {
    
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob() throws Exception {
            return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer()).listener(listener())
                .flow(step1()).end().build();
    }

    @Bean
    public Step step1() throws Exception {
        
        return this.stepBuilderFactory.get("step1").<ComnarconPersonDTO, ComnarconPerson>chunk(5)
            .reader(new ComnarconPersonReader())
            .processor(new ComnarconPersonItemProcessor())
            .writer(new ComnarconPersonWriter())
            .build();
    }
    
    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionListener();
    }
}
