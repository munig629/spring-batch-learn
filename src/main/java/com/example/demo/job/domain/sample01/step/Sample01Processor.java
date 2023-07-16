package com.example.demo.job.domain.sample01.step;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class Sample01Processor implements ItemProcessor<String, String> {

    @Override
    public String process(String item) throws Exception {
        
        try {
            item += "★";    
            System.out.println("Processor:" + item);
        } catch (Exception e) {
            throw new Exception();
            // return item;
        }
        
        return item;
    }
}
