package com.example.demo.job.domain.sample03;

import java.nio.charset.StandardCharsets;

import com.example.demo.job.domain.sample03.step.Sample03Processor;
import com.example.demo.job.domain.sample03.step.Sample03Tasklet;
import com.example.demo.job.domain.sample03.sql.Sample03DTO;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.FormatterLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import lombok.RequiredArgsConstructor;

@EnableBatchProcessing
@Configuration
@RequiredArgsConstructor
public class Sample03Configuration {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;
    
    private final Sample03Tasklet sample03Tasklet;

    private final Sample03Processor sample03Processor;

    @Bean
    public FlatFileItemReader<Sample03DTO> sample03FlatFileItemReader() {
        return new FlatFileItemReaderBuilder<Sample03DTO>()
                .name("userCsvReader")
                .resource(new ClassPathResource("com\\example\\demo\\job\\domain\\sample03\\data\\Sample03.csv"))
                .linesToSkip(1)   // ヘッダーをスキップ
                .encoding(StandardCharsets.UTF_8.name())
                .delimited()
                .names(new String[] {"id", "name", "birth_year", "birth_month", "birth_day", "sex", "memo"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Sample03DTO>(){
                    {
                        setTargetType(Sample03DTO.class);
                    }
                })
                .build();
    }

    @Bean
    public FlatFileItemWriter<Sample03DTO> sample03FlatFileItemWriter() {
        // 固定長ファイルを扱うので「FormatterLineAggregator」を利用
        FormatterLineAggregator<Sample03DTO> aggregator = new FormatterLineAggregator<Sample03DTO>();
        // フィールドの設定をしたいので、「BeanWrapperFieldExtractor」で実装
        BeanWrapperFieldExtractor<Sample03DTO> extractor = new BeanWrapperFieldExtractor<Sample03DTO>();
        extractor.setNames(new String[] {"id", "name", "birth_year", "birth_month", "birth_day", "sex", "memo"});
        aggregator.setFieldExtractor(extractor);
        // String.formatの形式で固定長を取り扱う
        aggregator.setFormat("%6s%-10s%05d%5s");

        return new FlatFileItemWriterBuilder<Sample03DTO>()
                .name("userCsvWriter")
                .resource(new FileSystemResource("src\\main\\resources\\com\\example\\demo\\job\\domain\\sample03\\data\\Sample03Out"))
                .encoding(StandardCharsets.UTF_8.name())
                .lineAggregator(aggregator)
                .transactional(false)
                .append(false)   // true：追記、false：新規作成
                .build();
    }

    // @Bean
    // public MyBatisBatchItemWriter<Sample03DTO> sample03MyBatisItemWriter() {
    //     Map<String, Object> params = new HashMap<>();
    //     params.put("id", "10");
    //     return new MyBatisBatchItemWriterBuilder<Sample03DTO>()
    //             .sqlSessionFactory(sqlSessionFactory)
    //             .statementId("com.example.demo.job.domain.sample03.sql.Sample03Mapper.update")
    //             .build();
    // }
    
    @Bean
    public Job sample03Job(Step sample03ChunkStep) {
        return jobBuilderFactory.get("sample03Job")
                .flow(sample03ChunkStep)
                .end()
                .build();
    }

    @Bean
    public Step sample0TaskletStep() {
        return stepBuilderFactory.get("sample03TaskletStep")
                .tasklet(sample03Tasklet)
                .build();
    }

    @Bean
    public Step sample03ChunkStep(ItemReader<Sample03DTO> reader, ItemWriter<Sample03DTO> writer) {
        return stepBuilderFactory.get("sample03ChunkStep")
                .<Sample03DTO, Sample03DTO>chunk(3)
                .reader(reader)
                .processor(sample03Processor)
                .writer(writer)
                .build();
    }
}
