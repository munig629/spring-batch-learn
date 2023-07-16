package com.example.demo.job.common.item;

import org.springframework.batch.item.ItemProcessor;

public abstract class BatchItemProcessorBase<I, O> implements ItemProcessor<I, O>{
    
    @Override
    public O process(I item) throws Exception {
        
        O result = null;
        
        try {
            result = doProcess(item);
        } catch (Exception e) {
            throw new Exception();
        }
        
        return result;
    }
    
    protected abstract O doProcess(I item) throws Exception ;
}
