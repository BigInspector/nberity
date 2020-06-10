package com.nberity.application.cronjobs.elkoproductsjob.steps.measurements;

import com.nberity.application.cronjobs.elkoproductsjob.service.ElkoProductsJobService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElkoProductsMeasurementsItemWriter implements ItemWriter<Long> {

    @Autowired
    private ElkoProductsJobService elkoProductsJobService;

    @Override
    public void write(List<? extends Long> items) {
        long start = System.currentTimeMillis();
        for (Long elkoCode : items) {
            elkoProductsJobService.updateElkoProductMeasurements(elkoCode);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time elapsed for 1000 batch: " + ((end - start) / 1000 ));
    }
}
