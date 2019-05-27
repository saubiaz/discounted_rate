package com.clicktap.digital.service;

import com.clicktap.digital.model.DiscountRateResponse;
import com.clicktap.digital.model.DiscountRequest;
import com.clicktap.digital.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DiscountRateServiceTest {


    @InjectMocks
    private DiscountRateServiceImpl discountRateServiceImpl = Mockito.spy(new DiscountRateServiceImpl());

    private DiscountRateResponse discountRateResponse=null;

    @Before
    public void init() {
        getDiscountRequestForEmployee();
        getDiscountRequestForAfiliate();
        getDiscountRequestForCustomerOverTwoYears();
        getDiscountRequestForOtherCustomers();
        discountRateResponse = getDiscountRateResponse();
       List<Item> items=getItems();
    }

    private List<Item> getItems() {

        List<Item> items = new ArrayList<>();

        Item item1 = new Item();
        item1.setAmount(BigDecimal.valueOf(23));
        item1.setCategory("GROCERY");

        Item item2 = new Item();
        item2.setAmount(BigDecimal.valueOf(400));
        item2.setCategory("FURNITURE");
        items.add(item2);

        Item item3 = new Item();
        item3.setAmount(BigDecimal.valueOf(100));
        item3.setCategory("BOOKS");
        items.add(item3);

        return items;
    }



    private DiscountRateResponse getDiscountRateResponse() {
        DiscountRateResponse discountRateResponse = new DiscountRateResponse();
        discountRateResponse.setNetPayableAmount(BigDecimal.valueOf(2));
        discountRateResponse.setDiscount(BigDecimal.valueOf(2));
        return discountRateResponse;
    }

    private DiscountRequest getDiscountRequestForEmployee() {
        DiscountRequest discountRequest = new DiscountRequest();

        discountRequest.setDoj("2016-05-26");
        discountRequest.setItemList(getItems());
        discountRequest.setStatus("E");
        return discountRequest;
    }

    private DiscountRequest getDiscountRequestForAfiliate() {
        DiscountRequest discountRequest = new DiscountRequest();

        discountRequest.setDoj("2016-05-26");
        discountRequest.setItemList(getItems());
        discountRequest.setStatus("A");
        return discountRequest;
    }

    private DiscountRequest getDiscountRequestForCustomerOverTwoYears() {
        DiscountRequest discountRequest = new DiscountRequest();

        discountRequest.setDoj("2016-05-26");
        discountRequest.setItemList(getItems());
        discountRequest.setStatus("O");
        return discountRequest;
    }

    private DiscountRequest getDiscountRequestForOtherCustomers() {
        DiscountRequest discountRequest = new DiscountRequest();

        discountRequest.setDoj("2019-05-26");
        discountRequest.setItemList(getItems());
        discountRequest.setStatus("O");
        return discountRequest;
    }


    @Test
    public void testContextLoads() {

        Mockito.when(discountRateServiceImpl.calculateTotalAmount(getItems())).thenReturn(BigDecimal.valueOf(2));

        Mockito.when(discountRateServiceImpl.getDiscountAmount(getItems())).thenReturn(BigDecimal.valueOf(2));
        Mockito.when(discountRateServiceImpl.checkDateValidForDiscount("2016-05-26")).thenReturn(true);

        discountRateResponse=discountRateServiceImpl.getDiscountedRate(getDiscountRequestForEmployee());
        discountRateResponse=discountRateServiceImpl.getDiscountedRate(getDiscountRequestForAfiliate());
        discountRateResponse=discountRateServiceImpl.getDiscountedRate(getDiscountRequestForCustomerOverTwoYears());
        discountRateResponse= discountRateServiceImpl.getDiscountedRate(getDiscountRequestForOtherCustomers());
        assertNotNull(discountRateResponse);

    }

}
