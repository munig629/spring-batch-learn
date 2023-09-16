package com.example.demo.job.common.item;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.item.ItemProcessor;

/**
 * Abstract Class implementing the ItemProcessor interface.
 */
public abstract class BatchItemProcessorBase<I, O> implements ItemProcessor<I, O> {
    
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

    // StepExecutionListenerのafterStepメソッドに対応したアノテーション
    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("BatchStepExecutionListener#afterStep()");

        // 処理件数
        System.out.println("■処理件数：" + stepExecution.getReadCount());
        // 成功件数（=書き込み件数）
        System.out.println("■成功件数：" + stepExecution.getWriteCount());

        return ExitStatus.COMPLETED;
    }
}
