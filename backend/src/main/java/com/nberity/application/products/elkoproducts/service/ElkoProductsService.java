package com.nberity.application.products.elkoproducts.service;

import com.nberity.application.products.elkoproducts.dto.ElkoProductDTO;
import com.nberity.application.products.elkoproducts.entity.ElkoProduct;
import com.nberity.application.products.elkoproducts.repository.ElkoProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElkoProductsService {

    @Autowired
    private ElkoProductsRepository elkoProductsRepository;

    public List<ElkoProduct> getLatestElkoProducts() {
        return elkoProductsRepository.findAll();
    }

    public Page<ElkoProduct> getLatestElkoProductsByPageNrAndSize(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return elkoProductsRepository.findAll(pageable);
    }

    public List<ElkoProductDTO> convertEntitiesToDto(List<ElkoProduct> elkoProductList) {
        List<ElkoProductDTO> elkoProductDTOs = new ArrayList<>();
        for (ElkoProduct product : elkoProductList) {
            elkoProductDTOs.add(elkoProductToDto(product));
        }
        return elkoProductDTOs;
    }

    public Page<ElkoProductDTO> convertElkoProductPageToDtoPage(Page<ElkoProduct> elkoProductPage) {
        Page<ElkoProductDTO> dtoPage = elkoProductPage.map(elkoProduct -> elkoProductToDto(elkoProduct));
        return dtoPage;
    }

    private ElkoProductDTO elkoProductToDto(ElkoProduct product) {
        ElkoProductDTO elkoProductDTO = new ElkoProductDTO();
        elkoProductDTO.setId(product.getId());
        elkoProductDTO.setElkoCode(product.getElkoCode());
        elkoProductDTO.setName(product.getName());
        elkoProductDTO.setManufacturerCode(product.getManufacturerCode());
        elkoProductDTO.setVendorName(product.getVendorName());
        elkoProductDTO.setVendorCode(product.getVendorCode());
        elkoProductDTO.setCatalog(product.getCatalog());
        elkoProductDTO.setQuantity(product.getQuantity());
        elkoProductDTO.setPrice(product.getPrice());
        elkoProductDTO.setDiscountPrice(product.getDiscountPrice());
        elkoProductDTO.setImagePath(product.getImagePath());
        elkoProductDTO.setThumbnailImagePath(product.getThumbnailImagePath());
        elkoProductDTO.setFullDsc(product.getFullDsc());
        elkoProductDTO.setCurrency(product.getCurrency());
        elkoProductDTO.setHttpDescription(product.getHttpDescription());
        elkoProductDTO.setPackagingQuantity(product.getPackagingQuantity());
        elkoProductDTO.setWarranty(product.getWarranty());
        elkoProductDTO.setEanCode(product.getEanCode());
        elkoProductDTO.setObligatoryKit(product.getObligatoryKit());
        elkoProductDTO.setReservedQuantity(product.getReservedQuantity());
        elkoProductDTO.setPromDate(product.getPromDate());
        elkoProductDTO.setPromQuant(product.getPromQuant());
        elkoProductDTO.setQuantityForPrice2(product.getQuantityForPrice2());
        elkoProductDTO.setPrice2(product.getPrice2());
        elkoProductDTO.setLotNumber(product.getLotNumber());
        elkoProductDTO.setCopyrightTax(product.getCopyrightTax());
        elkoProductDTO.setIncomingQuantity(product.getIncomingQuantity());
        return elkoProductDTO;
    }
}
