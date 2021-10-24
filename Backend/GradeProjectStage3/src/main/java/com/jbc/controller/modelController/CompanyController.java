package com.jbc.controller.modelController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbc.controller.modelController.api.CompanyControllerApi;
import com.jbc.exception.CustomException;
import com.jbc.message.request.CouponRequest;
import com.jbc.message.response.ExceptionResponse;
import com.jbc.message.response.SuccessResponse;
import com.jbc.model.Coupon;
import com.jbc.model.user.Company;
import com.jbc.service.user.ifc.CompanyServiceIfc;
import com.jbc.util.controllerUtil.TokenUtil;
import com.jbc.util.modelUtil.CategoryUtil;

/**
 * {@code Controller} used to handle methods from the
 * {@link com.jbc.service.user.ifc.CompanyServiceIfc} {@code Service}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see ifc#CompanyServiceIfc
 * @see controllerUtil#TokenUtil
 * @see user#Company
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/company")
public class CompanyController implements CompanyControllerApi, TokenUtil {

	/* attributes */
	@Autowired
	private CompanyServiceIfc companyServ;

	/* methods */
	@Override
	public ResponseEntity<?> addCoupon(CouponRequest couponReq) {
		try {
			return new ResponseEntity<Coupon>(companyServ.addCoupon(getUserId(), couponReq.toCoupon()), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> updateCoupon(CouponRequest couponReq) {
		try {
			return new ResponseEntity<Coupon>(companyServ.updateCoupon(getUserId(), couponReq.toCoupon()),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> deleteCoupon(Long couponId) {
		try {
			companyServ.deleteCoupon(getUserId(), couponId);
			return new ResponseEntity<SuccessResponse>(new SuccessResponse("Coupon deleted succesfully"),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCompanyCoupons() {
		try {
			return new ResponseEntity<List<Coupon>>(companyServ.getCompanyCoupons(getUserId()), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCompanyCoupons(Double maxPrice) {
		try {
			return new ResponseEntity<List<Coupon>>(companyServ.getCompanyCoupons(getUserId(), maxPrice),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCompanyCoupons(CategoryUtil category) {
		try {
			return new ResponseEntity<List<Coupon>>(companyServ.getCompanyCoupons(getUserId(), category),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCompanyDetails() {
		return new ResponseEntity<Company>(companyServ.getCompanyDetails(getUserId()), HttpStatus.OK);
	}

}
