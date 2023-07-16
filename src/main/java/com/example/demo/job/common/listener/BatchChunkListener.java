package com.example.demo.job.common.listener;

import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.listener.ChunkListenerSupport;

public class BatchChunkListener extends ChunkListenerSupport {

    @Override
    public void beforeChunk(ChunkContext context) {
        System.out.println("BatchChunkListener#beforeChunk()");
    }

    @Override
    public void afterChunk(ChunkContext context) {
        System.out.println("BatchChunkListener#afterChunk()");
    }   
    
    @Override
    public void afterChunkError(ChunkContext context) {
        System.out.println("BatchChunkListener#afterChunkError()");
    }
}
