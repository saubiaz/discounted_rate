package com.clicktap.digital.service;

import com.clicktap.digital.model.DiscountRateResponse;
import com.clicktap.digital.model.DiscountRequest;
import com.clicktap.digital.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class DiscountRateServiceImpl  implements DiscountRateService{


    @Override
    public DiscountRateResponse getDiscountedRate(DiscountRequest discountRequest) {

        /**
         * 1.Calculate the total amount of all the items for that customer.
         * 2.Check the status of the customer (Employee(E),Afiliate(A),Others(O))
         * 3.Calculate the discounted rate for the items by not including the Grocery category.
         * 4. Subtract the total amount with the discounted amount to get the net payable amount
         */

        DiscountRateResponse discountRateResponse = new DiscountRateResponse();

        if(!CollectionUtils.isEmpty(discountRequest.getItemList())) {
            BigDecimal totalAmount = calculateTotalAmount(discountRequest.getItemList());
            if (!StringUtils.isEmpty(discountRequest.getStatus()) && discountRequest.getStatus().equalsIgnoreCase("E")) {
                discountRateResponse.setDiscount(getDiscountAmount(discountRequest.getItemList()).multiply(BigDecimal.valueOf(30)).divide(BigDecimal.valueOf(100)));
            } else if (!StringUtils.isEmpty(discountRequest.getStatus()) && discountRequest.getStatus().equalsIgnoreCase("A")) {
                discountRateResponse.setDiscount(getDiscountAmount(discountRequest.getItemList()).multiply(BigDecimal.valueOf(10)).divide(BigDecimal.valueOf(100)));
            } else if (checkDateValidForDiscount(discountRequest.getDoj())) {
                discountRateResponse.setDiscount(getDiscountAmount(discountRequest.getItemList()).multiply(BigDecimal.valueOf(5)).divide(BigDecimal.valueOf(100)));
            } else {
                if (totalAmount.compareTo(BigDecimal.valueOf(99.0)) > 0) {
                    discountRateResponse.setDiscount(totalAmount.multiply(BigDecimal.valueOf(5)).divide(BigDecimal.valueOf(100)));
                }else {
                    discountRateResponse.setDiscount(BigDecimal.ZERO);
                }

            }
            discountRateResponse.setNetPayableAmount(totalAmount.subtract(discountRateResponse.getDiscount()));
        }else{
            //throw error that the items are not present in the request
        }

        return discountRateResponse;
    }

    public BigDecimal calculateTotalAmount(List<Item> itemList) {
        /**
         *   calculate the total amount
         */

        return itemList.stream().map(Item::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean checkDateValidForDiscount(String doj) {
        // Range = End date - Start date
        long range = ChronoUnit.DAYS.between(!StringUtils.isEmpty(doj)?LocalDate.parse(doj):LocalDate.now() , LocalDate.now());
        return range >= (365 * 2);
    }

    public BigDecimal getDiscountAmount(List<Item> itemList) {
        /**
         * calculate the amount by not including the Grocery category
          */

        return itemList.stream().filter(item -> !item.getCategory().equalsIgnoreCase("GROCERY")).map(Item::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
