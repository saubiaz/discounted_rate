package com.clicktap.digital.assignment;

import com.clicktap.digital.controller.RetailStoreDiscountController;
import com.clicktap.digital.service.DiscountRateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {RetailStoreDiscountController.class, DiscountRateService.class})
@EntityScan("com.clicktap.digital.model")
public class AssignmentApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

}
