package com.example.demo.job.domain.sample01.step;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class Sample01Tasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        
        try {
            System.out.println("Sample01Job：Hello, World!");
        } catch (Exception e) {
            return RepeatStatus.FINISHED;
        }
        
        return RepeatStatus.FINISHED;
    }
}
