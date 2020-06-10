package com.nberity.application.cronjobs.elkoproductsjob.steps.measurements;

import com.nberity.application.cronjobs.elkoproductsjob.service.ElkoProductsJobService;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElkoProductsMeasurementsItemReader implements ItemReader<Long> {

    @Autowired
    private ElkoProductsJobService elkoProductsJobService;

    private List<Long> elkoProductsElkoCodes;

    private int index = 0;

    @BeforeStep
    public void beforeStep() {
        elkoProductsElkoCodes = elkoProductsJobService.getAllLatestProductsElkoCodes();
    }

    @Override
    public Long read() {
        if (index < elkoProductsElkoCodes.size()) {
            return elkoProductsElkoCodes.get(index++);
        } else {
            return null;
        }
    }
}
