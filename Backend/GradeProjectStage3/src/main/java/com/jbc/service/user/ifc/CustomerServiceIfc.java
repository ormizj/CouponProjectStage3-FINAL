package com.jbc.service.user.ifc;

import java.util.Set;

import com.jbc.exception.couponException.CouponExpiredPurchaseException;
import com.jbc.exception.couponException.CouponIsEmptyException;
import com.jbc.exception.couponException.CouponNotFoundException;
import com.jbc.exception.couponException.CouponOwnedException;
import com.jbc.exception.couponException.CouponPurchaseNoStockException;
import com.jbc.model.Coupon;
import com.jbc.model.user.Customer;
import com.jbc.util.modelUtil.CategoryUtil;

/**
 * {@code interface} that contains the methods used by the
 * {@link com.jbc.service.user.CustomerService} {@code Service} to ensure the
 * business logic of the system, related to the
 * {@link com.jbc.model.user.Customer} {@code Entity}s.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see ifc#ClientLogin
 * @see user#Customer
 * @see user#CustomerService
 */
public interface CustomerServiceIfc extends ClientLogin {

	/**
	 * 
	 * @param email
	 * @return {@code true} if there is an existing
	 *         {@link com.jbc.model.user.Customer} {@code Entity} with the
	 *         {@code email} in the system. {@code false} if there isn't.
	 * @see user#Customer
	 */
	boolean customerExists(String email);

	/**
	 * Adds a {@link com.jbc.model.Coupon} {@code Entity}, based on the
	 * {@code couponId} to a {@link com.jbc.model.user.Customer} {@code Entity},
	 * based on the {@code customerId} to the system.
	 * 
	 * @param customerId
	 * @param couponId
	 * @return the purchased {@code Coupon} {@code Entity}.
	 * @throws CouponOwnedException           if the {@code Customer} {@code Entity}
	 *                                        based on the {@code customerId},
	 *                                        already owns the {@code Coupon}
	 *                                        {@code Entity} based on the
	 *                                        {@code couponId}.
	 * @throws CouponNotFoundException        if a {@code Coupon} {@code Entity}
	 *                                        with the {@code couponId} does not
	 *                                        exist in the system
	 * @throws CouponPurchaseNoStockException if the {@code Coupon} {@code Entity}
	 *                                        {@code amount} is 0 or below.
	 * @throws CouponExpiredPurchaseException if the {@code Coupon} {@code Entity}
	 *                                        {@code endDate} value is expired.
	 * @see user#Customer
	 * @see model#Coupon
	 */
	public Coupon purchaseCoupon(long customerId, long couponId) throws CouponOwnedException, CouponNotFoundException,
			CouponPurchaseNoStockException, CouponExpiredPurchaseException;

	/**
	 * 
	 * @param customerId
	 * @return the {@code Set} of the {@link com.jbc.model.Coupon} {@code Entity}s
	 *         that exist in the system, that belong to the
	 *         {@link com.jbc.model.user.Customer} {@code Entity} based on the
	 *         {@code customerId}.
	 * @throws CouponIsEmptyException if there is not a single {@code Coupon}
	 *                                {@code Entity} in the system that belongs to
	 *                                the {@code Customer} {@code Entity} based on
	 *                                the {@code customerId}.
	 * @see user#Customer
	 * @see model#Coupon
	 */
	public Set<Coupon> getCustomerCoupons(long customerId) throws CouponIsEmptyException;

	/**
	 * 
	 * @param customerId
	 * @param maxPrice
	 * @return the {@code Set} of the {@link com.jbc.model.Coupon} {@code Entity}s
	 *         that exist in the system, that belong to the
	 *         {@link com.jbc.model.user.Customer} {@code Entity} based on the
	 *         {@code customerId} and the {@code maxPrice}.
	 * @throws CouponNotFoundException if there is not a single {@code Coupon}
	 *                                 {@code Entity} in the system that belongs to
	 *                                 the {@code Customer} {@code Entity} based on
	 *                                 the {@code customerId} and the
	 *                                 {@code maxPrice}.
	 * @see user#Customer
	 * @see model#Coupon
	 */
	public Set<Coupon> getCustomerCoupons(long customerId, double maxPrice) throws CouponNotFoundException;

	/**
	 * 
	 * @param customerId
	 * @param category
	 * @return the {@code Set} of the {@link com.jbc.model.Coupon} {@code Entity}s
	 *         that exist in the system, that belong to the
	 *         {@link com.jbc.model.user.Customer} {@code Entity} based on the
	 *         {@code customerId} and the
	 *         {@link com.jbc.util.modelUtil.CategoryUtil}.
	 * @throws CouponNotFoundException if there is not a single {@code Coupon}
	 *                                 {@code Entity} in the system that belongs to
	 *                                 the {@code Customer} {@code Entity} based on
	 *                                 the {@code customerId} and the
	 *                                 {@code category}.
	 * @see modelUtil#CategoryUtil
	 * @see user#Customer
	 * @see model#Coupon
	 */
	public Set<Coupon> getCustomerCoupons(long customerId, CategoryUtil category) throws CouponNotFoundException;

	/**
	 * 
	 * @param customerId
	 * @return the {@link com.jbc.model.user.Customer} {@code Entity} based on the
	 *         {@code customerId}.
	 * @see user#Customer
	 */
	public Customer getCustomerDetails(long customerId);

}
