package com.jbc.util.serviceUtil.loggerUtil;

import com.jbc.model.Coupon;

/**
 * {@code interface} containing {@code default} methods related to the
 * attributes of a {@link com.jbc.model.Coupon} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see model#Coupon
 */
public interface CouponLoggerAttributer {

	/**
	 * 
	 * @param coupon
	 * @return the attributes of the {@code Coupon} as a {@code String}.
	 */
	default public String attributeCoupon(Coupon coupon) {
		return "Company Id: " + coupon.getCompany().getId() + ", Category: " + coupon.getCategory() + ", Title: "
				+ coupon.getTitle() + ", Description: " + coupon.getDescription() + ", Start Date: "
				+ coupon.getStartDate() + ", End Date: " + coupon.getEndDate() + ", Amount: " + coupon.getAmount()
				+ ", Price: " + coupon.getPrice() + ", Image: " + coupon.getImage();
	}

}
