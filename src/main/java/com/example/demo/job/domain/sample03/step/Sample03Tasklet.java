package com.example.demo.job.domain.sample03.step;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.demo.job.common.step.BatchTaskletBase;

@Component
@StepScope
public class Sample03Tasklet extends BatchTaskletBase {
    @Override
    public RepeatStatus doExecute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        
        System.out.println("Sample03Job：Hello, World!");
        return RepeatStatus.FINISHED;
    }
}
