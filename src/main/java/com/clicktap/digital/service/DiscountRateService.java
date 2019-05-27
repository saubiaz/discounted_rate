package com.clicktap.digital.service;

import com.clicktap.digital.model.DiscountRateResponse;
import com.clicktap.digital.model.DiscountRequest;

public interface DiscountRateService {

    public DiscountRateResponse getDiscountedRate(DiscountRequest discountRequest);

}
