package com.jbc.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbc.exception.CustomExceptionList;
import com.jbc.exception.couponException.CouponAlreadyExistsException;
import com.jbc.exception.couponException.CouponDuplicateValueException;
import com.jbc.exception.couponException.CouponIsEmptyException;
import com.jbc.exception.couponException.CouponIsNullException;
import com.jbc.exception.couponException.CouponNotFoundException;
import com.jbc.exception.couponException.CouponNullValueException;
import com.jbc.model.Coupon;
import com.jbc.model.Logger;
import com.jbc.model.user.Company;
import com.jbc.repository.CouponRepository;
import com.jbc.repository.LoggerRepository;
import com.jbc.repository.user.CompanyRepository;
import com.jbc.service.user.ifc.CompanyServiceIfc;
import com.jbc.util.generalUtil.StringClass;
import com.jbc.util.modelUtil.CategoryUtil;
import com.jbc.util.modelUtil.ModelEntityUtil;
import com.jbc.util.serviceUtil.CouponHelper;
import com.jbc.util.serviceUtil.ModelActionUtil;
import com.jbc.util.serviceUtil.UserTypeUtil;
import com.jbc.util.serviceUtil.loggerUtil.CompanyLoggerAttributer;
import com.jbc.util.serviceUtil.loggerUtil.CouponLoggerAttributer;
import com.jbc.util.serviceUtil.validationUtil.CouponValidation;

/**
 * {@code Service} that {@code implements} multiple {@code interface}s to ensure
 * the business logic of the system, related to the
 * {@link com.jbc.model.user.Company} {@code Entity} before executing to the
 * data-base.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see ifc#CompanyServiceIFC
 * @see validationUtil#CouponValidation
 * @see attributeUtil#CompanyLoggerAttributer
 * @see attributeUtil#CouponLoggerAttributer
 * @see generalUtil#CouponHelper
 * @see repository#user#CompanyRepository
 * @see repository#CouponRepository
 * @see repository#LoggerRepository
 */
@Service
public class CompanyService
		implements CompanyServiceIfc, CouponValidation, CouponLoggerAttributer, CompanyLoggerAttributer, CouponHelper {

	/* attributes */
	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private CouponRepository couponRepo;
	@Autowired
	private LoggerRepository loggerRepo;

	@Override
	public boolean login(String email, String password) {
		Optional<Company> optionCompany = companyRepo.findByEmailIgnoreCaseAndPassword(email, password);
		if (!optionCompany.isPresent())
			return false;
		Company loggedCompany = optionCompany.get();
		loggerRepo.save(new Logger(loggedCompany.getId(), UserTypeUtil.COMPANY, loggedCompany.getId(),
				ModelEntityUtil.COMPANY, ModelActionUtil.LOGIN, attributeCompany(loggedCompany), null));
		return true;
	}

	@Override
	public boolean companyExists(String email) {
		return companyRepo.findByEmailIgnoreCase(email).isPresent();
	}

	@Override
	public Coupon addCoupon(long companyId, Coupon addedCoupon) throws CouponNullValueException, CouponIsNullException,
			CouponAlreadyExistsException, CouponDuplicateValueException, CustomExceptionList {
		couponNullValidation(addedCoupon);
		if (couponRepo.existsById(addedCoupon.getId()))
			throw new CouponAlreadyExistsException(addedCoupon.getId());
		checkCoupon(addedCoupon, companyId);
		addedCoupon.setCompany(getCompanyDetails(companyId));
		addedCoupon = couponRepo.save(addedCoupon);
		loggerRepo.save(new Logger(companyId, UserTypeUtil.COMPANY, addedCoupon.getId(), ModelEntityUtil.COUPON,
				ModelActionUtil.CREATE, null, attributeCoupon(addedCoupon)));
		return addedCoupon;
	}

	@Override
	public Coupon updateCoupon(long companyId, Coupon updatedCoupon) throws CouponNullValueException,
			CouponIsNullException, CouponDuplicateValueException, CustomExceptionList, CouponNotFoundException {
		couponNullValidation(updatedCoupon);
		checkCoupon(updatedCoupon, companyId);
		Coupon oldCoupon;
		Optional<Coupon> optionCoupon;
		synchronized (StringClass.COUPON_ID_SYNC + updatedCoupon.getId()) {
			optionCoupon = couponRepo.findByIdAndCompanyId(updatedCoupon.getId(), companyId);
			if (!optionCoupon.isPresent())
				throw new CouponNotFoundException(updatedCoupon.getId());
			oldCoupon = optionCoupon.get();
			updatedCoupon.setCompany(oldCoupon.getCompany());
			updatedCoupon = couponRepo.save(updatedCoupon);
		}
		loggerRepo.save(new Logger(companyId, UserTypeUtil.COMPANY, updatedCoupon.getId(), ModelEntityUtil.COUPON,
				ModelActionUtil.UPDATE, attributeCoupon(oldCoupon), attributeCoupon(updatedCoupon)));
		return updatedCoupon;
	}

	@Override
	public void deleteCoupon(long companyId, long couponId) throws CouponNotFoundException {
		synchronized (StringClass.COUPON_ID_SYNC + couponId) {
			Optional<Coupon> couponOption = couponRepo.findByIdAndCompanyId(couponId, companyId);
			if (!couponOption.isPresent())
				throw new CouponNotFoundException(couponId);
			Coupon removedCoupon = couponOption.get();
			loggerRepo.save(new Logger(companyId, UserTypeUtil.COMPANY, removedCoupon.getId(), ModelEntityUtil.COUPON,
					ModelActionUtil.DELETE, attributeCoupon(removedCoupon), null));
			deleteCoupon(removedCoupon, couponRepo);
		}
	}

	@Override
	public List<Coupon> getCompanyCoupons(long companyId) throws CouponIsEmptyException {
		List<Coupon> coupons = couponRepo.findByCompanyId(companyId);
		if (coupons.isEmpty())
			throw new CouponIsEmptyException();
		return coupons;
	}

	@Override
	public List<Coupon> getCompanyCoupons(long companyId, double maxPrice) throws CouponNotFoundException {
		List<Coupon> coupons = couponRepo.findByCompanyIdAndPriceLessThanEqual(companyId, maxPrice);
		if (coupons.isEmpty())
			throw new CouponNotFoundException(maxPrice);
		return coupons;
	}

	@Override
	public List<Coupon> getCompanyCoupons(long companyId, CategoryUtil category) throws CouponNotFoundException {
		List<Coupon> coupons = couponRepo.findByCompanyIdAndCategory(companyId, category);
		if (coupons.isEmpty())
			throw new CouponNotFoundException(category);
		return coupons;
	}

	@Override
	public Company getCompanyDetails(long companyId) {
		return companyRepo.findById(companyId).get();
	}

	/**
	 * Helper method, checks if a {@code Coupon} {@code Entity} with the same title
	 * already exists in the same {@code Company} {@code Entity} based on the
	 * {@code companyId}.
	 * 
	 * @param coupon
	 * @param companyId
	 * @throws CouponDuplicateValueException if the {@code Company} {@code Entity}
	 *                                       already has an existing {@code Coupon}
	 *                                       {@code Entity} with the same
	 *                                       {@code title}.
	 */
	private void checkCoupon(Coupon coupon, long companyId) throws CouponDuplicateValueException {
		if (couponRepo.findByIdNotAndCompanyIdAndTitleIgnoreCase(coupon.getId(), companyId, coupon.getTitle())
				.isPresent())
			throw new CouponDuplicateValueException(coupon.getTitle());
	};

}
