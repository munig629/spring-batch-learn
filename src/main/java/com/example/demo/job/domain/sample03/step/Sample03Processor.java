package com.example.demo.job.domain.sample03.step;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;
import com.example.demo.job.domain.sample03.sql.Sample03DTO;

import com.example.demo.job.common.item.BatchItemProcessorBase;

@Component
@StepScope
public class Sample03Processor extends BatchItemProcessorBase<Sample03DTO, Sample03DTO> {

    @Override
    public Sample03DTO doProcess(Sample03DTO item) throws Exception {
        
        // 男の場合、名前の末尾に"◆"を追加
        if("1".equals(item.getSex())){
            item.setName(item.getName() + "◆");
        }
        System.out.println("Processor:" + item.toString());
        
        return item;
    }
}
