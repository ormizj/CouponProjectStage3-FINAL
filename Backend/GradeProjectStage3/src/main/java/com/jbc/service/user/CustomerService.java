package com.jbc.service.user;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbc.exception.couponException.CouponExpiredPurchaseException;
import com.jbc.exception.couponException.CouponIsEmptyException;
import com.jbc.exception.couponException.CouponNotFoundException;
import com.jbc.exception.couponException.CouponOwnedException;
import com.jbc.exception.couponException.CouponPurchaseNoStockException;
import com.jbc.model.Coupon;
import com.jbc.model.Logger;
import com.jbc.model.user.Customer;
import com.jbc.repository.CouponRepository;
import com.jbc.repository.LoggerRepository;
import com.jbc.repository.user.CustomerRepository;
import com.jbc.service.user.ifc.CustomerServiceIfc;
import com.jbc.util.generalUtil.StringClass;
import com.jbc.util.generalUtil.TimeComparisonUtil;
import com.jbc.util.modelUtil.CategoryUtil;
import com.jbc.util.modelUtil.ModelEntityUtil;
import com.jbc.util.serviceUtil.ModelActionUtil;
import com.jbc.util.serviceUtil.UserTypeUtil;
import com.jbc.util.serviceUtil.loggerUtil.CouponLoggerAttributer;
import com.jbc.util.serviceUtil.loggerUtil.CustomerLoggerAttributer;

/**
 * {@code Service} that {@code implements} multiple {@code interface}s to ensure
 * the business logic of the system, related to the
 * {@link com.jbc.model.user.Customer} {@code Entity} before executing to the
 * data-base.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see ifc#CustomerServiceIFC
 * @see attributeUtil#CustomerLoggerAttributer
 * @see attributeUtil#CouponLoggerAttributer
 * @see generalUtil#TimeComparisonUtil
 * @see user#CustomerRepository
 * @see user#CouponRepository
 * @see repository#LoggerRepository
 */
@Service
public class CustomerService
		implements CustomerServiceIfc, TimeComparisonUtil, CouponLoggerAttributer, CustomerLoggerAttributer {

	/* attributes */
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private CouponRepository couponRepo;
	@Autowired
	private LoggerRepository loggerRepo;

	@Override
	public boolean login(String email, String password) {
		Optional<Customer> optionCustomer = customerRepo.findByEmailIgnoreCaseAndPassword(email, password);
		if (!optionCustomer.isPresent())
			return false;
		Customer loggedCustomer = optionCustomer.get();
		loggerRepo.save(new Logger(loggedCustomer.getId(), UserTypeUtil.CUSTOMER, loggedCustomer.getId(),
				ModelEntityUtil.CUSTOMER, ModelActionUtil.LOGIN, attributeCustomer(loggedCustomer), null));
		return true;
	}

	@Override
	public boolean customerExists(String email) {
		return customerRepo.findByEmailIgnoreCase(email).isPresent();
	}

	@Override
	public Coupon purchaseCoupon(long customerId, long couponId) throws CouponOwnedException, CouponNotFoundException,
			CouponPurchaseNoStockException, CouponExpiredPurchaseException {
		synchronized (StringClass.COUPON_ID_SYNC + couponId) {
			if (couponRepo.findByCustomerIdAndCouponId(customerId, couponId).isPresent())
				throw new CouponOwnedException(couponId);
			Optional<Coupon> couponOption = couponRepo.findById(couponId);
			if (!couponOption.isPresent())
				throw new CouponNotFoundException(couponId);
			Coupon purchasedCoupon = couponOption.get();
			if (purchasedCoupon.getAmount() <= 0)
				throw new CouponPurchaseNoStockException(couponId);
			if (isPast(purchasedCoupon.getEndDate())) {
				throw new CouponExpiredPurchaseException(purchasedCoupon.getId(), purchasedCoupon.getEndDate());
			}
			Customer customer = getCustomerDetails(customerId);
			purchasedCoupon.setAmount(purchasedCoupon.getAmount() - 1);
			customer.getCoupons().add(purchasedCoupon);
			customerRepo.save(customer);
			loggerRepo.save(new Logger(customerId, UserTypeUtil.CUSTOMER, purchasedCoupon.getId(),
					ModelEntityUtil.COUPON, ModelActionUtil.PURCHASE, null, attributeCoupon(purchasedCoupon)));
			return couponRepo.save(purchasedCoupon);
		}
	}

	@Override
	public Set<Coupon> getCustomerCoupons(long customerId) throws CouponIsEmptyException {
		Set<Coupon> coupons = couponRepo.findByCustomerId(customerId);
		if (coupons.isEmpty())
			throw new CouponIsEmptyException();
		return coupons;
	}

	@Override
	public Set<Coupon> getCustomerCoupons(long customerId, double maxPrice) throws CouponNotFoundException {
		Set<Coupon> coupons = couponRepo.findByCustomerIdAndCouponPrice(customerId, maxPrice);
		if (coupons.isEmpty())
			throw new CouponNotFoundException(maxPrice);
		return coupons;
	}

	@Override
	public Set<Coupon> getCustomerCoupons(long customerId, CategoryUtil category) throws CouponNotFoundException {
		Set<Coupon> coupons = couponRepo.findByCustomerIdAndCouponCategory(customerId, category);
		if (coupons.isEmpty())
			throw new CouponNotFoundException(category);
		return coupons;
	}

	@Override
	public Customer getCustomerDetails(long customerId) {
		return customerRepo.findById(customerId).get();
	}

}
