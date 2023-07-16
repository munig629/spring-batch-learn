package com.example.demo.job.common.listener;

import org.springframework.batch.core.ItemReadListener;

public class BatchItemReadListener<T> implements ItemReadListener<T> {

    @Override
    public void beforeRead() {
        System.out.println("BatchItemReadListener#beforeRead()");
    }

    @Override
    public void afterRead(T item) {
        System.out.println("BatchItemReadListener#afterRead()");
    }   
    
    @Override
    public void onReadError(Exception ex) {
        System.out.println("BatchItemReadListener#onReadError()");
    }
}
