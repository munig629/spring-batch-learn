package com.example.demo.job.domain.sample02;

import java.util.Map;
import java.util.HashMap;

import com.example.demo.job.common.listener.BatchJobExecutionListener;
import com.example.demo.job.common.listener.BatchChunkListener;
import com.example.demo.job.common.listener.BatchItemReadListener;
import com.example.demo.job.common.listener.BatchItemProcessListener;
import com.example.demo.job.common.listener.BatchItemWriteListener;
import com.example.demo.job.domain.sample02.step.Sample02Processor;
import com.example.demo.job.domain.sample02.step.Sample02Tasklet;
import com.example.demo.job.domain.sample02.sql.Sample02DTO;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.ChunkListener;
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
public class Sample02Configuration {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;
    
    private final SqlSessionFactory sqlSessionFactory;

    private final Sample02Tasklet sample02Tasklet;
    
    private final Sample02Processor sample02Processor;

    @Bean
    public MyBatisCursorItemReader<Sample02DTO> sample02MyBatisItemReader() {
        return new MyBatisCursorItemReaderBuilder<Sample02DTO>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.example.demo.job.domain.sample02.sql.Sample02Mapper.findAll")
                .build();
    }

    @Bean
    public MyBatisBatchItemWriter<Sample02DTO> sample02MyBatisItemWriter() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", "10");
        return new MyBatisBatchItemWriterBuilder<Sample02DTO>()
                .sqlSessionFactory(sqlSessionFactory)
                .statementId("com.example.demo.job.domain.sample02.sql.Sample02Mapper.update")
                .build();
    }
    
    @Bean
    public Job sample02Job(Step sample02ChunkStep) {
        return jobBuilderFactory.get("sample02Job")
                .listener(jobExecutionListener())
                .flow(sample02ChunkStep)
                .end()
                .build();
    }

    @Bean
    public Step sample02TaskletStep() {
        return stepBuilderFactory.get("sample02TaskletStep")
                .tasklet(sample02Tasklet)
                .build();
    }
    
    @Bean
    public Step sample02ChunkStep(ItemReader<Sample02DTO> reader, ItemWriter<Sample02DTO> writer) {
        return stepBuilderFactory.get("sample02ChunkStep")
                .<Sample02DTO, Sample02DTO>chunk(4)
                .reader(reader).listener(itemReadListener())
                .processor(sample02Processor).listener(itemProcessListener())
                .writer(writer).listener(itemWriteListener())
                .listener(chunkListener())
                .build();
    }
    
    @Bean
	public JobExecutionListener jobExecutionListener() {
		return new BatchJobExecutionListener();
	}
	
	@Bean
	public ChunkListener chunkListener() {
		return new BatchChunkListener();
	}
	
	@Bean
	public ItemReadListener<Sample02DTO> itemReadListener() {
		return new BatchItemReadListener<Sample02DTO>();
	}
	
	@Bean
	public ItemProcessListener<Sample02DTO, Sample02DTO> itemProcessListener() {
		return new BatchItemProcessListener<Sample02DTO, Sample02DTO> ();
	}
	
	@Bean
	public ItemWriteListener<Sample02DTO> itemWriteListener() {
		return new BatchItemWriteListener<Sample02DTO>();
	}
}
