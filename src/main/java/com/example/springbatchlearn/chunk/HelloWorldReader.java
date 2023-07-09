package com.example.springbatchlearn.chunk;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class HelloWorldReader implements ItemReader<String> {
    
    // 出力文字用
    private String[] input = {"Hello", "World", "hoge", "fuga", null, "The World"};
    private int index = 0;
    
    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        
        // 配列の文字列を取得
        String message = input[index++];
        System.out.println("Read:{}" + message);
        
        return message;
    }
}
