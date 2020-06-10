package com.nberity.application.cronjobs.elkoproductsjob.steps.allproducts;

import com.nberity.application.cronjobs.elkoproductsjob.service.ElkoProductsJobService;
import com.nberity.application.products.elkoproducts.entity.ElkoProduct;
import com.nberity.application.cronjobs.elkoproductsjob.service.ElkoProductsJobWSClient;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElkoProductsJobItemReader implements ItemReader<ElkoProduct> {

    private ElkoProductsJobWSClient elkoProductsJobWSClient;

    private ElkoProductsJobService elkoProductsJobService;

    @Autowired
    private ElkoProductsJobItemReader(ElkoProductsJobWSClient elkoProductsJobWSClient,
                                     ElkoProductsJobService elkoProductsJobService) {
        this.elkoProductsJobWSClient = elkoProductsJobWSClient;
        this.elkoProductsJobService = elkoProductsJobService;

    }

    private List<ElkoProduct> elkoProducts;

    private int index = 0;

    private Integer latestElkoProductsVersionNr = 0;

    @BeforeStep
    public void gatherAllElkoProducts(StepExecution stepExecution) {
        System.out.println("Starting to gather all ELKO products from WS...");
        elkoProducts = elkoProductsJobWSClient.getAllElkoProducts();

        System.out.println("Figuring out the latest product version nr...");
        latestElkoProductsVersionNr = elkoProductsJobService.getMostRecentProductsVersionNr() + 1;
        stepExecution.getJobExecution().getExecutionContext().putInt("latestVersionNr", latestElkoProductsVersionNr);
        System.out.println("Version nr for the new products shall be: " + latestElkoProductsVersionNr);
    }

    @Override
    public ElkoProduct read() {
        if (index < elkoProducts.size()) {
            return elkoProducts.get(index++);
        } else {
            return null;
        }
    }
}
