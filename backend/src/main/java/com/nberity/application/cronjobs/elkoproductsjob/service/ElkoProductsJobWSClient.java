package com.nberity.application.cronjobs.elkoproductsjob.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nberity.application.products.elkoproducts.entity.ElkoProduct;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ElkoProductsJobWSClient {

    private OkHttpClient client;

    private static final Long READ_TIMEOUT = 10L;

    private static final String BASE_WS_URL = "https://api.elkogroup.com/v3.0/api";

    private MediaType mediaType = MediaType.parse("application/json");

    private ElkoProductsJobWSClient() {
        initializeHttpClient();
    }

    // Gets all elko products wihtout measurements
    public List<ElkoProduct> getAllElkoProducts() {
        String bearerToken = retrieveBearerToken();
        String allElkoProductsJsonArrayString = getAllElkoProductsJsonArrayString(bearerToken);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList(mapper.readValue(allElkoProductsJsonArrayString, ElkoProduct[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getProductInfo(Long elkoProduct) {
        String bearerToken = retrieveBearerToken();
        return getProductInformation(bearerToken, elkoProduct);
    }

    private String getProductInformation(String bearerToken, Long elkoCode) {
        Request elkoProductDescription = new Request.Builder()
                .url(BASE_WS_URL + "/Catalog/Products/" + elkoCode + "/Description")
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();
        Response descriptionResponse;

        try {
            descriptionResponse = client.newCall(elkoProductDescription).execute();
            return descriptionResponse.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getAllElkoProductsJsonArrayString(String bearerToken) {
        Request allElkoProductsRequest = new Request.Builder()
                .url("https://api.elkogroup.com/v3.0/api/Catalog/Products")
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();
        Response response2;

        try {
            response2 = client.newCall(allElkoProductsRequest).execute();
            return response2.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String retrieveBearerToken() {
        RequestBody body = RequestBody.create(mediaType, "{\n\"username\": \"itoleg\",\n\"password\": \"euyUU4H4FaJFBzL\"\n}");
        Request request = new Request.Builder()
                .url(BASE_WS_URL + "/Token")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "text/plain")
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initializeHttpClient() {
        client = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
                .build();
    }

}
