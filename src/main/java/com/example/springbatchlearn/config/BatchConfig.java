package com.example.springbatchlearn.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private Tasklet helloWorldTasklet;
    
    @Autowired
    private ItemReader helloWorldReader;
    
    @Autowired
    private ItemProcessor helloWorldProcessor;
    
    @Autowired
    private ItemWriter helloWorldWriter;

    @Bean
    public Job helloWorldJob() {
        return jobBuilderFactory.get("helloWorldJob")
                .incrementer(new RunIdIncrementer())
                .start(helloWorldChunkStep())
                .build();
    }

    @Bean
    public Step helloWorldTaskletStep() {
        return stepBuilderFactory.get("helloWorldTaskletStep")
                .tasklet(helloWorldTasklet)
                .build();
    }
    
    @Bean
    public Step helloWorldChunkStep() {
        return stepBuilderFactory.get("helloWorldChunkStep")
                .<String, String>chunk(1)   // チャンクの設定、括弧内はコミット間隔
                .reader(helloWorldReader)
                .processor(helloWorldProcessor)
                .writer(helloWorldWriter)
                .build();
    }
}
