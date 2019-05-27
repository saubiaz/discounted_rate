package com.clicktap.digital.model;


import java.math.BigDecimal;

public class DiscountRateResponse {

	private BigDecimal netPayableAmount;
	private BigDecimal discount;

	public BigDecimal getNetPayableAmount() {
		return netPayableAmount;
	}

	public void setNetPayableAmount(BigDecimal netPayableAmount) {
		this.netPayableAmount = netPayableAmount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
}
