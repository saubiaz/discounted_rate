package com.clicktap.digital.controller;


import com.clicktap.digital.model.DiscountRateResponse;
import com.clicktap.digital.model.DiscountRequest;
import com.clicktap.digital.service.DiscountRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/rest/retailStore")
public class RetailStoreDiscountController {


    @Autowired
    DiscountRateService discountRateService;

    @PostMapping(value = "/discountRate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DiscountRateResponse> getDiscountRates(@Valid @RequestBody DiscountRequest discountRequest, BindingResult bindingResult) {

        return new ResponseEntity<>(discountRateService.getDiscountedRate(discountRequest), HttpStatus.OK);
    }

}
