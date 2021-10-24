package com.jbc.service.user.ifc;

import java.util.List;

import com.jbc.exception.CustomExceptionList;
import com.jbc.exception.couponException.CouponAlreadyExistsException;
import com.jbc.exception.couponException.CouponDuplicateValueException;
import com.jbc.exception.couponException.CouponIsEmptyException;
import com.jbc.exception.couponException.CouponIsNullException;
import com.jbc.exception.couponException.CouponNotFoundException;
import com.jbc.exception.couponException.CouponNullValueException;
import com.jbc.model.Coupon;
import com.jbc.model.user.Company;
import com.jbc.util.modelUtil.CategoryUtil;

/**
 * {@code interface} that contains the methods used by the
 * {@link com.jbc.service.user.CompanyService} {@code Service} to ensure the
 * business logic of the system, related to the
 * {@link com.jbc.model.user.Company} {@code Entity}s.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see ifc#ClientLogin
 * @see user#Company
 * @see user#CompanyService
 */
public interface CompanyServiceIfc extends ClientLogin {

	/**
	 * 
	 * @param email
	 * @return {@code true} if there is an existing
	 *         {@link com.jbc.model.user.Company} {@code Entity} with the
	 *         {@code email} in the system. {@code false} if there isn't.
	 * @see user#Company
	 */
	boolean companyExists(String email);

	/**
	 * Adds a new {@link com.jbc.model.Coupon} {@code Entity} to the system that
	 * will belong to the {@link com.jbc.model.user.Company} {@code Entity} based on
	 * the {@code companyId}.
	 * 
	 * @param companyId
	 * @param coupon
	 * @return the added {@code Coupon} {@code Entity}.
	 * @throws CouponNullValueException      if the {@code Coupon} value is
	 *                                       {@code null}.
	 * @throws CouponIsNullException         if the {@code Coupon} value is
	 *                                       {@code null}.
	 * @throws CouponAlreadyExistsException  if a {@code Coupon} {@code Entity} with
	 *                                       the same {@code id} already exists in
	 *                                       the system.
	 * @throws CouponDuplicateValueException if the {@code Company} {@code Entity}
	 *                                       with the {@code companyId} already has
	 *                                       an existing {@code Coupon}
	 *                                       {@code Entity} with the same
	 *                                       {@code title}.
	 * @throws CustomExceptionList           if multiple {@code Exception}s are
	 *                                       possible to occur at once.
	 * @see model#user#Company
	 * @see model#Coupon
	 */
	public Coupon addCoupon(long companyId, Coupon coupon) throws CouponNullValueException, CouponIsNullException,
			CouponAlreadyExistsException, CouponDuplicateValueException, CustomExceptionList;

	/**
	 * Updates a {@link com.jbc.model.Coupon} {@code Entity} based on the the
	 * {@code couponId} that belongs to the {@link com.jbc.model.user.Company}
	 * {@code Entity} based on the {@code companyId}, and exists in the system.
	 * 
	 * @param companyId
	 * @param coupon
	 * @return the updated {@code Coupon} {@code Entity}.
	 * @throws CouponNullValueException      if the {@code Coupon} value is
	 *                                       {@code null}.
	 * @throws CouponIsNullException         if the {@code Coupon} value is
	 *                                       {@code null}.
	 * @throws CouponDuplicateValueException if the {@code Company} {@code Entity}
	 *                                       with the {@code companyId} already has
	 *                                       an existing {@code Coupon}
	 *                                       {@code Entity} with the same
	 *                                       {@code title}.
	 * @throws CouponNotFoundException       if a {@code Coupon} {@code Entity} with
	 *                                       the {@code couponId} does not exist in
	 *                                       the system or doesn't belong to the
	 *                                       {@code Company} {@code Entity} with the
	 *                                       {@code companyId}.
	 * @throws CustomExceptionList           if multiple {@code Exception}s are
	 *                                       possible to occur at once.
	 * @see user#Company
	 * @see model#Coupon
	 */
	public Coupon updateCoupon(long companyId, Coupon coupon) throws CouponNullValueException, CouponIsNullException,
			CouponDuplicateValueException, CustomExceptionList, CouponNotFoundException;

	/**
	 * Deletes a {@link com.jbc.model.Coupon} {@code Entity} based on the the
	 * {@code couponId} that belongs to the {@link com.jbc.model.user.Company}
	 * {@code Entity} based on the {@code companyId}.
	 * 
	 * @param companyId
	 * @param couponId
	 * @throws CouponNotFoundException if a {@code Coupon} {@code Entity} with the
	 *                                 {@code couponId} does not exist in the system
	 *                                 or doesn't belong to the {@code Company}
	 *                                 {@code Entity} with the {@code companyId}.
	 * @see model#user#Company
	 * @see model#Coupon
	 */
	public void deleteCoupon(long companyId, long couponId) throws CouponNotFoundException;

	/**
	 * 
	 * @param companyId
	 * @return the {@code List} of the {@link com.jbc.model.Coupon} {@code Entity}s
	 *         that exist in the system, that belong to the
	 *         {@link com.jbc.model.user.Company} {@code Entity} based on the
	 *         {@code companyId}.
	 * @throws CouponIsEmptyException if there is not a single {@code Coupon}
	 *                                {@code Entity} in the system that belongs to
	 *                                the {@code Company} {@code Entity} based on
	 *                                the {@code companyId}.
	 * @see user#Company
	 * @see model#Coupon
	 */
	public List<Coupon> getCompanyCoupons(long companyId) throws CouponIsEmptyException;

	/**
	 * 
	 * @param companyId
	 * @param maxPrice
	 * @return the {@code List} of the {@link com.jbc.model.Coupon} {@code Entity}s
	 *         that exist in the system, that belong to the
	 *         {@link com.jbc.model.user.Company} {@code Entity} based on the
	 *         {@code companyId} and the {@code maxPrice}.
	 * @throws CouponNotFoundException if there is not a single {@code Coupon}
	 *                                 {@code Entity} in the system that belongs to
	 *                                 the {@code Company} {@code Entity} based on
	 *                                 the {@code companyId} and the
	 *                                 {@code maxPrice}.
	 * @see user#Company
	 * @see model#Coupon
	 */
	public List<Coupon> getCompanyCoupons(long companyId, double maxPrice) throws CouponNotFoundException;

	/**
	 * 
	 * @param companyId
	 * @param category
	 * @return the {@code List} of the {@link com.jbc.model.Coupon} {@code Entity}s
	 *         that exist in the system, that belong to the
	 *         {@link com.jbc.model.user.Company} {@code Entity} based on the
	 *         {@code companyId} and the
	 *         {@link com.jbc.util.modelUtil.CategoryUtil}.
	 * @throws CouponNotFoundException if there is not a single {@code Coupon}
	 *                                 {@code Entity} in the system that belongs to
	 *                                 the {@code Company} {@code Entity} based on
	 *                                 the {@code companyId} and the
	 *                                 {@code category}.
	 * @see modelUtil#CategoryUtil
	 * @see model#user#Company
	 * @see model#Coupon
	 */
	public List<Coupon> getCompanyCoupons(long companyId, CategoryUtil category) throws CouponNotFoundException;

	/**
	 * 
	 * @param companyId
	 * @return the {@link com.jbc.model.user.Company} {@code Entity} based on the
	 *         {@code companyId}.
	 * @see user#Company
	 */
	public Company getCompanyDetails(long companyId);

}
