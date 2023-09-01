package com.example.demo.job.domain.sample03;

import java.util.Map;
import java.util.HashMap;

import com.example.demo.job.domain.sample03.step.Sample03Processor;
import com.example.demo.job.domain.sample03.sql.Sample03DTO;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import lombok.RequiredArgsConstructor;

@EnableBatchProcessing
@Configuration
@RequiredArgsConstructor
public class Sample03Configuration {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;
    
    private final SqlSessionFactory sqlSessionFactory;
    
    private final Sample03Processor sample03Processor;

    @Bean
    public MyBatisCursorItemReader<Sample03DTO> sample03MyBatisItemReader() {
        return new MyBatisCursorItemReaderBuilder<Sample03DTO>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.example.demo.job.domain.sample03.sql.Sample03Mapper.findAll")
                .build();
    }

    @Bean
    public MyBatisBatchItemWriter<Sample03DTO> sample03MyBatisItemWriter() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", "10");
        return new MyBatisBatchItemWriterBuilder<Sample03DTO>()
                .sqlSessionFactory(sqlSessionFactory)
                .statementId("com.example.demo.job.domain.sample03.sql.Sample03Mapper.update")
                .build();
    }
    
    @Bean
    public Job sample03Job(Step sample03ChunkStep) {
        return jobBuilderFactory.get("sample03Job")
                .flow(sample03ChunkStep)
                .end()
                .build();
    }

    @Bean
    public Step sample03ChunkStep(ItemReader<Sample03DTO> reader, ItemWriter<Sample03DTO> writer) {
        return stepBuilderFactory.get("sample03ChunkStep")
                .<Sample03DTO, Sample03DTO>chunk(5)
                .reader(reader)
                .processor(sample03Processor)
                .writer(writer)
                .build();
    }
}
