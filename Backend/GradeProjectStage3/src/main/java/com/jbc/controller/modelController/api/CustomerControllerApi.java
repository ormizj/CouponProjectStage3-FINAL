package com.jbc.controller.modelController.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jbc.message.response.ExceptionResponse;
import com.jbc.model.Coupon;
import com.jbc.model.user.Customer;
import com.jbc.util.modelUtil.CategoryUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Api for Swagger used to document methods from the
 * {@link com.jbc.service.user.ifc.CustomerServiceIfc} {@code Service}.
 * <p>
 * Used by the {@link com.jbc.controller.modelController.CustomerController}
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see modelController.CustomerController
 * @see ifc#CustomerServiceIfc
 * @see user#Customer
 */
public interface CustomerControllerApi {

	/* methods */
	@ApiOperation(value = "Purchase Coupon", notes = "Purchases a Coupon for the Customer")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Coupon.class),
			@ApiResponse(code = 400, message = "Customer Coupon purchase failed", response = ExceptionResponse.class) })
	@PutMapping(value = "/purchase-coupon", produces = "application/json")
	public ResponseEntity<?> purchaseCoupon(
			@ApiParam(value = "ID of the Coupon to purchase", required = true) @RequestParam Long couponId);

	@ApiOperation(value = "Get Customer Coupons", notes = "Get Customer purchased Coupons")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Coupon.class),
			@ApiResponse(code = 400, message = "Customer Coupons not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-coupons", produces = "application/json")
	public ResponseEntity<?> getCustomerCoupons();

	@ApiOperation(value = "Get Customer Coupons maximum price", notes = "Get Customer purchased Coupons by maximum price")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Coupon.class),
			@ApiResponse(code = 400, message = "Customer Coupons not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-coupons-max-price", produces = "application/json")
	public ResponseEntity<?> getCustomerCoupons(
			@ApiParam(value = "Maximum price of the Coupon", required = true) @RequestParam Double maxPrice);

	@ApiOperation(value = "Get Customer Coupons category", notes = "Get Customer purchased Coupons by category")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Coupon.class),
			@ApiResponse(code = 400, message = "Customer Coupons not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-coupons-category", produces = "application/json")
	public ResponseEntity<?> getCustomerCoupons(
			@ApiParam(value = "Category type of the Coupon", required = true) @RequestParam CategoryUtil category);

	@ApiOperation(value = "Get Details", notes = "Returns the information of the Customer",response = Customer.class)
	@ApiResponse(code = 200, message = "Succesful operation", response = Customer.class)
	@GetMapping(value = "/get-details", produces = "application/json")
	public ResponseEntity<?> getCustomerDetails();

}
