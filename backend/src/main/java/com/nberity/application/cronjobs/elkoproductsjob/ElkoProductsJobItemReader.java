package com.nberity.application.cronjobs.elkoproductsjob;

import com.nberity.application.products.elkoproducts.entity.ElkoProduct;
import com.nberity.application.cronjobs.elkoproductsjob.service.ElkoProductsJobService;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElkoProductsJobItemReader implements ItemReader<ElkoProduct> {

    @Autowired
    private ElkoProductsJobService elkoProductsJobService;

    private List<ElkoProduct> elkoProducts;

    private int index = 0;

    @BeforeStep
    public void gatherAllElkoProducts() {
        System.out.println("ksfdksjgks");
  //      elkoProducts = elkoProductsJobService.getAllElkoProducts();
    }


    @Override
    public ElkoProduct read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("HAH HAHA");
        if (index < elkoProducts.size()) {
            return elkoProducts.get(index++);
        } else {
            return null;
        }
    }
}
