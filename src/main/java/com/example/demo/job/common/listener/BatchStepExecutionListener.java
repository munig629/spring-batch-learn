package com.example.demo.job.common.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

public class BatchStepExecutionListener extends StepExecutionListenerSupport {
    
    // @Override
    // public void beforeStep(StepExecution stepExecution) {
    //     System.out.println("BatchStepExecutionListener#beforeStep()");
    // }

    // @Override
    // public ExitStatus afterStep(StepExecution stepExecution) {
    //     System.out.println("BatchStepExecutionListener#afterStep()");

    //     // 処理件数
    //     System.out.println("■処理件数：" + stepExecution.getReadCount());
    //     // 成功件数（=書き込み件数）
    //     System.out.println("■成功件数：" + stepExecution.getWriteCount());

    //     return ExitStatus.COMPLETED;
    // }
}
