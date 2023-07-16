package com.example.demo.job.common.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class BatchJobExecutionListener extends JobExecutionListenerSupport {
    
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("BatchJobExecutionListener#beforeJob()");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("BatchJobExecutionListener#afterJob()");
    }
}
