package com.example.demo.job.common.listener;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;

public class BatchItemWriteListener<S> implements ItemWriteListener<S> {
    
    @Override
    public void beforeWrite(List<? extends S> items) {
        System.out.println("BatchItemWriteListener#beforeWrite()");
    }

    @Override
    public void afterWrite(List<? extends S> items) {
        System.out.println("BatchItemWriteListener#afterWrite()");
    }
    
    @Override
    public void onWriteError(Exception exception, List<? extends S> items) {
        System.out.println("BatchItemWriteListener#onWriteError()");
    }
}
