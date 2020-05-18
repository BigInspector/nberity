package com.nberity.application.cronjobs.elkoproductsjob;

import com.nberity.application.products.elkoproducts.entity.ElkoProduct;
import com.nberity.application.cronjobs.elkoproductsjob.repository.ElkoProductsJobRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElkoProductsJobItemWriter implements ItemWriter<ElkoProduct> {

    private int total = 0;

    @Autowired
    private ElkoProductsJobRepository elkoProductsJobRepository;

    @Override
    public void write(List<? extends ElkoProduct> items) throws Exception {
        total += items.size();
        long start = System.currentTimeMillis();

 //       elkoProductsJobRepository.saveAll(items);
        long finish = System.currentTimeMillis();
        System.out.println("Time elapsed: " + ((finish - start) / 1000 ));
    }
}
