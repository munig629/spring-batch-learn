package com.example.springbatchlearn.chunk;

import java.util.List;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class HelloWorldWriter implements ItemWriter<String> {
    
    @Override
    public void write(List<? extends String> items) throws Exception {
        
        System.out.println("Write:{}" + items);
        System.out.println("============");
    }
}
