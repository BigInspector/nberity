package com.nberity.application.products.elkoproducts.controller;

import com.nberity.application.products.elkoproducts.dto.ElkoProductDTO;
import com.nberity.application.products.elkoproducts.entity.ElkoProduct;
import com.nberity.application.products.elkoproducts.service.ElkoProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ElkoProductsController {

    @Autowired
    private ElkoProductsService elkoProductsService;

    @RequestMapping("/products/latest-elko-products")
    public List<ElkoProductDTO> getLatestElkoProducts() {
        List<ElkoProduct> elkoProductList = elkoProductsService.getLatestElkoProducts();
        System.out.println(elkoProductList);
        return elkoProductsService.convertEntitiesToDto(elkoProductList);
    }

    @GetMapping("/products/get-page")
    public Page<ElkoProductDTO> getElkoProductPage(@RequestParam("page") int pageNumber , @RequestParam("size") int pageSize) {
        // we subtract one number from input pageNumber, because Angular displays first page as 1 but Spring looks data for first page at index 0
        Page<ElkoProduct> elkoProductPage = elkoProductsService.getLatestElkoProductsByPageNrAndSize(pageNumber - 1, pageSize);
        return elkoProductsService.convertElkoProductPageToDtoPage(elkoProductPage);
    }

}
