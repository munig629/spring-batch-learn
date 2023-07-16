package com.example.demo.job.domain.sample02.step;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;
import com.example.demo.job.domain.sample02.sql.Sample02DTO;

import com.example.demo.job.common.item.BatchItemProcessorBase;

@Component
@StepScope
public class Sample02Processor extends BatchItemProcessorBase<Sample02DTO, Sample02DTO> {

    @Override
    public Sample02DTO doProcess(Sample02DTO item) throws Exception {
        
        // 男の場合、名前の末尾に"★"を追加
        if("1".equals(item.getSex())){
            item.setName(item.getName() + "★");
        }
        System.out.println("Processor:" + item.toString());
        
        return item;
    }
}
