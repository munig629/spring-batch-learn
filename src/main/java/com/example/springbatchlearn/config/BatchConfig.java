package com.example.springbatchlearn.config;

import com.example.springbatchlearn.tasklet.HelloWorldTasklet;
import com.example.springbatchlearn.chunk.HelloWorldReader;
import com.example.springbatchlearn.chunk.HelloWorldProcessor;
import com.example.springbatchlearn.chunk.HelloWorldWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final HelloWorldTasklet helloWorldTasklet;
    
    private final HelloWorldReader helloWorldReader;
    
    private final HelloWorldProcessor helloWorldProcessor;
    
    private final HelloWorldWriter helloWorldWriter;

    public BatchConfig(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory,
                       HelloWorldTasklet helloWorldTasklet,
                       HelloWorldReader helloWorldReader,
                       HelloWorldProcessor helloWorldProcessor,
                       HelloWorldWriter helloWorldWriter
                       ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.helloWorldTasklet = helloWorldTasklet;
        this.helloWorldReader = helloWorldReader;
        this.helloWorldProcessor = helloWorldProcessor;
        this.helloWorldWriter = helloWorldWriter;
    }

    @Bean
    public Job helloWorldJob(Step helloWorldChunkStep) {
        return jobBuilderFactory.get("helloWorldJob")
                .flow(helloWorldChunkStep)
                .end()
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
                .<String, String>chunk(3)   // チャンクの設定、括弧内はコミット間隔
                .reader(helloWorldReader)
                .processor(helloWorldProcessor)
                .writer(helloWorldWriter)
                .build();
    }
}
