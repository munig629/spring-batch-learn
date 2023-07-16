package com.example.demo.job.common.step;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * Abstract Class implementing the Tasklet interface.
 */
public abstract class BatchTaskletBase implements Tasklet {
    
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        
        try {
            doExecute(contribution, chunkContext);
        } catch (Exception e) {
            throw new Exception();
            // return RepeatStatus.FINISHED;
        }
        
        return RepeatStatus.FINISHED;
    }
    
    protected abstract RepeatStatus doExecute(StepContribution contribution, ChunkContext chunkContext) throws Exception ;
}
