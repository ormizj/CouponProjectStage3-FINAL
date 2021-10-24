package com.jbc.controller.modelController;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbc.controller.modelController.api.CustomerControllerApi;
import com.jbc.exception.CustomException;
import com.jbc.message.response.ExceptionResponse;
import com.jbc.model.Coupon;
import com.jbc.model.user.Customer;
import com.jbc.service.user.ifc.CustomerServiceIfc;
import com.jbc.util.controllerUtil.TokenUtil;
import com.jbc.util.modelUtil.CategoryUtil;

/**
 * {@code Controller} used to handle methods from the
 * {@link com.jbc.service.user.ifc.CustomerServiceIfc} {@code Service}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see ifc#CustomerServiceIfc
 * @see controllerUtil#TokenUtil
 * @see user#Customer
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController implements CustomerControllerApi, TokenUtil {

	/* attributes */
	@Autowired
	private CustomerServiceIfc customerServ;

	/* methods */
	@Override
	public ResponseEntity<?> purchaseCoupon(Long couponId) {
		try {
			return new ResponseEntity<Coupon>(customerServ.purchaseCoupon(getUserId(), couponId), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCustomerCoupons() {
		try {
			return new ResponseEntity<Set<Coupon>>(customerServ.getCustomerCoupons(getUserId()), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCustomerCoupons(Double maxPrice) {
		try {
			return new ResponseEntity<Set<Coupon>>(customerServ.getCustomerCoupons(getUserId(), maxPrice),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCustomerCoupons(CategoryUtil category) {
		try {
			return new ResponseEntity<Set<Coupon>>(customerServ.getCustomerCoupons(getUserId(), category),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCustomerDetails() {
		return new ResponseEntity<Customer>(customerServ.getCustomerDetails(getUserId()), HttpStatus.OK);
	}

}
