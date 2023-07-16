package com.example.demo.job.domain.sample02.step;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.demo.job.common.step.BatchTaskletBase;

@Component
@StepScope
public class Sample02Tasklet extends BatchTaskletBase {
    @Override
    public RepeatStatus doExecute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        
        System.out.println("Sample02Job：Hello, World!");
        return RepeatStatus.FINISHED;
    }
}
