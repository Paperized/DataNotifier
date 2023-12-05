package com.paperized.shopapi.controller;

import com.paperized.generated.shopapi.api.AmazonApi;
import com.paperized.generated.shopapi.model.AmazonProductTracked;
import com.paperized.shopapi.dquery.QueryCondition;
import com.paperized.shopapi.service.AmazonService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmazonController implements AmazonApi {
    private final AmazonService amazonService;

    public AmazonController(AmazonService amazonService) {
        this.amazonService = amazonService;
    }

    @Override
    public ResponseEntity<AmazonProductTracked> findProductDetails(String url, QueryCondition body) throws Exception {
        AmazonProductTracked amazonProductTracked = amazonService.findProductDetails(url);

        // just for testing, works!!
        if(!body.evaluate(amazonProductTracked.getItem()))
            throw new EntityNotFoundException("Entity did not pass the query check!");

        return ResponseEntity.ok(amazonProductTracked);
    }
}
