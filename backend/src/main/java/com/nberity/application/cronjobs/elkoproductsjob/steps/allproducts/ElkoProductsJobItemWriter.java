package com.nberity.application.cronjobs.elkoproductsjob.steps.allproducts;

import com.nberity.application.cronjobs.elkoproductsjob.service.ElkoProductsJobService;
import com.nberity.application.products.elkoproducts.entity.ElkoProduct;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElkoProductsJobItemWriter implements ItemWriter<ElkoProduct> {

    @Autowired
    private ElkoProductsJobService elkoProductsJobService;

    private int latestElkoProductsVersionNr;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        latestElkoProductsVersionNr = stepExecution.getJobExecution().getExecutionContext().getInt("latestVersionNr");
    }

    @Override
    public void write(List<? extends ElkoProduct> items) {
        System.out.println("Beginning");
        System.out.println("Length: " + items.size());
        long start = System.currentTimeMillis();
        setLatestVersionNrToElkoProducts(items);
        elkoProductsJobService.saveAllElkoProducts((List<ElkoProduct>) items);
        long finish = System.currentTimeMillis();
        System.out.println("Time elapsed: " + ((finish - start) / 1000 ));
    }

    private void setLatestVersionNrToElkoProducts(List<? extends ElkoProduct> items) {
        for (ElkoProduct product : items) {
            product.setVersionNr(latestElkoProductsVersionNr);
        }
    }
}
