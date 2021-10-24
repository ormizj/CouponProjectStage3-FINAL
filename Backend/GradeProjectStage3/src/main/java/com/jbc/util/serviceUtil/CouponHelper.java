package com.jbc.util.serviceUtil;

import com.jbc.model.Coupon;
import com.jbc.repository.CouponRepository;

/**
 * {@code interface} containing {@code default} methods related to the
 * {@link com.jbc.model.Coupon} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see model#Coupon
 */
public interface CouponHelper {

	/**
	 * Deletes a {@link com.jbc.model.Coupon} {@code Entity} based on the entered
	 * {@code Coupon}.
	 * 
	 * @param coupon
	 * @param couponRepo
	 */
	public default void deleteCoupon(Coupon coupon, CouponRepository couponRepo) {
		coupon.setCustomers(null);
		coupon.setCompany(null);
		couponRepo.save(coupon);
		couponRepo.delete(coupon);
	}

}
