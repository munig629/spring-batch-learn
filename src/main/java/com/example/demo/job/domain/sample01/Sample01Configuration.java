package com.example.demo.job.domain.sample01;

import com.example.demo.job.domain.sample01.step.Sample01Processor;
import com.example.demo.job.domain.sample01.step.Sample01Reader;
import com.example.demo.job.domain.sample01.step.Sample01Tasklet;
import com.example.demo.job.domain.sample01.step.Sample01Writer;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class Sample01Configuration {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final Sample01Tasklet sample01Tasklet;
    
    private final Sample01Reader sample01Reader;
    
    private final Sample01Processor sample01Processor;
    
    private final Sample01Writer sample01Writer;

    public Sample01Configuration(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory,
                       Sample01Tasklet sample01Tasklet,
                       Sample01Reader sample01Reader,
                       Sample01Processor sample01Processor,
                       Sample01Writer sample01Writer) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.sample01Tasklet = sample01Tasklet;
        this.sample01Reader = sample01Reader;
        this.sample01Processor = sample01Processor;
        this.sample01Writer = sample01Writer;
    }

    @Bean
    public Job sample01Job(Step sample01ChunkStep) {
        return jobBuilderFactory.get("sample01Job")
                .flow(sample01ChunkStep)
                .end()
                .build();
    }

    @Bean
    public Step sample01TaskletStep() {
        return stepBuilderFactory.get("sample01TaskletStep")
                .tasklet(sample01Tasklet)
                .build();
    }
    
    @Bean
    public Step sample01ChunkStep() {
        return stepBuilderFactory.get("sample01ChunkStep")
                .<String, String>chunk(3)
                .reader(sample01Reader)
                .processor(sample01Processor)
                .writer(sample01Writer)
                .build();
    }
}
