package com.nberity.application.cronjobs.elkoproductsjob.service;

import com.nberity.application.products.elkoproducts.entity.ElkoProduct;
import com.nberity.application.products.elkoproducts.repository.ElkoProductsRepository;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ElkoProductsJobService {

    private ElkoProductsRepository elkoProductsRepository;

    private ElkoProductsJobWSClient elkoProductsJobWSClient;

    private static final String UNIT_GROSS_WEIGHT = "UNIT_GROSS_WEIGHT";

    private static final String UNIT_GROSS_WEIGHT_UNIT = "UNIT_GROSS_WEIGHT_UNIT";

    @Autowired
    public ElkoProductsJobService(ElkoProductsRepository elkoProductsRepository,
                                  ElkoProductsJobWSClient elkoProductsJobWSClient) {
        this.elkoProductsRepository = elkoProductsRepository;
        this.elkoProductsJobWSClient = elkoProductsJobWSClient;
    }

    public List<ElkoProduct> getAllElkoProducts() {
        return elkoProductsJobWSClient.getAllElkoProducts();
    }

    public Integer getMostRecentProductsVersionNr() {
        Integer topVersionNr = elkoProductsRepository.getTopVersionNumber();
        if (topVersionNr == null) {
            topVersionNr = 0;
        }
        return topVersionNr;
    }

    public void updateElkoProductMeasurements(Long elkoCode) {
        String productInfo = elkoProductsJobWSClient.getProductInfo(elkoCode);
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(productInfo);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                if (object.get("criteria").equals("Unit Net Weight")) {
                    elkoProductsRepository.updateProductNetWeight(object.get("value").toString(), object.get("measurement").toString(), elkoCode);
                } else if (object.get("criteria").equals("Unit Gross Weight")) {
                    elkoProductsRepository.updateProductGrossWeight(object.get("value").toString(), object.get("measurement").toString(), elkoCode);
                } else if (object.get("criteria").equals("Shipping Box Height")) {
                    elkoProductsRepository.updateShippingBoxHeight(object.get("value").toString(), object.get("measurement").toString(), elkoCode);
                } else if (object.get("criteria").equals("Shipping Box Width")) {
                    elkoProductsRepository.updateShippingBoxWidth(object.get("value").toString(), object.get("measurement").toString(), elkoCode);
                } else if (object.get("criteria").equals("Shipping Box Depth")) {
                    elkoProductsRepository.updateShippingBoxDepth(object.get("value").toString(), object.get("measurement").toString(), elkoCode);
                } else if (object.get("criteria").equals("Shipping Box Weight")) {
                    elkoProductsRepository.updateShippingBoxWeight(object.get("value").toString(), object.get("measurement").toString(), elkoCode);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Only for testing database connection
    public Integer getTestNumber() {
        return elkoProductsRepository.getTestNumber();
    }

    public void deleteOldVersionNumbers() {
        elkoProductsRepository.deleteOldVersionNumbers();
    }

    public List<Long> getAllLatestProductsElkoCodes() {
        return elkoProductsRepository.getAllLatestProductsElkoCodes();
    }

    public void saveAllElkoProducts(List<ElkoProduct> items) {
        elkoProductsRepository.saveAll(items);
    }


    public void updateElkoProductsMeasurements() {

    }
}
