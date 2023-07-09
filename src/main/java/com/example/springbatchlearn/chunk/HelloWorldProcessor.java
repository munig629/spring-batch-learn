package com.example.springbatchlearn.chunk;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class HelloWorldProcessor implements ItemProcessor<String, String> {
    
    @Override
    public String process(String item) throws Exception {
        
        // 文字列の加工
        item = item + "★";
        System.out.println("Processor:{}" + item);
        return item;
    }
}
