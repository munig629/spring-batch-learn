package com.example.demo.job.domain.sample01.step;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class Sample01Writer implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> items) throws Exception {
        
        for (String item: items) {
            System.out.println("Writer:" + item);
        }
        System.out.println("====================");
    }
}
