package com.example.demo.job.domain.sample01.step;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class Sample01Reader implements ItemReader<String> {

    private String items[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", null};
    
    private int index = 0;
    
    @Override
    public String read() throws Exception {
        
        String item = items[index];
        index++;
        System.out.println("Reader:" + item);
        
        return item;
    }
}
