package com.example.demo.job.common.listener;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.lang.Nullable;

public class BatchItemProcessListener<T, S> implements ItemProcessListener<T, S> {

    @Override
    public void beforeProcess(T item) {
        System.out.println("BatchItemProcessListener#beforeProcess()");
    }

    @Override
    public void afterProcess(T item, @Nullable S result) {
        System.out.println("BatchItemProcessListener#afterProcess()");
    }   
    
    @Override
    public void onProcessError(T item, Exception e) {
        System.out.println("BatchItemProcessListener#onProcessError()");
    }   
}
