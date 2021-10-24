package com.jbc.controller.modelController.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jbc.message.request.CouponRequest;
import com.jbc.message.response.ExceptionResponse;
import com.jbc.message.response.SuccessResponse;
import com.jbc.model.Coupon;
import com.jbc.model.user.Company;
import com.jbc.util.modelUtil.CategoryUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Api for Swagger used to document methods from the
 * {@link com.jbc.service.user.ifc.CompanyServiceIfc} {@code Service}.
 * <p>
 * Used by the {@link com.jbc.controller.modelController.CompanyController}
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see modelController.CompanyController
 * @see ifc#CompanyServiceIfc
 * @see user#Company
 */
public interface CompanyControllerApi {

	/* methods */
	@ApiOperation(value = "Add Coupon", notes = "Creates a Coupon for the Company and returns it\n\n[id - unnecessary]")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Coupon.class),
			@ApiResponse(code = 400, message = "Coupon creation failed", response = ExceptionResponse.class) })
	@PostMapping(value = "/add-coupon", produces = "application/json")
	public ResponseEntity<?> addCoupon(@RequestBody CouponRequest couponReq);

	@ApiOperation(value = "Update Coupon", notes = "Updates a Coupon from the Company and returns it")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Coupon.class),
			@ApiResponse(code = 400, message = "Coupon update failed", response = ExceptionResponse.class) })
	@PutMapping(value = "/update-coupon", produces = "application/json")
	public ResponseEntity<?> updateCoupon(@RequestBody CouponRequest couponReq);

	@ApiOperation(value = "Delete Coupon", notes = "Deletes a Coupon from the Company")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = SuccessResponse.class),
			@ApiResponse(code = 400, message = "Coupon deletion failed", response = ExceptionResponse.class) })
	@DeleteMapping(value = "/delete-coupon", produces = "application/json")
	public ResponseEntity<?> deleteCoupon(
			@ApiParam(value = "ID of the Coupon to delete", required = true, example = "0") @RequestParam Long couponId);

	@ApiOperation(value = "Get Company Coupons", notes = "Get Company owned Coupons")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Coupon.class),
			@ApiResponse(code = 400, message = "Company Coupons not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-coupons", produces = "application/json")
	public ResponseEntity<?> getCompanyCoupons();

	@ApiOperation(value = "Get Company Coupons maximum price", notes = "Get Company owned Coupons by maximum price")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Coupon.class),
			@ApiResponse(code = 400, message = "Company Coupons not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-coupons-max-price", produces = "application/json")
	public ResponseEntity<?> getCompanyCoupons(
			@ApiParam(value = "Maximum price of the Coupon", required = true) @RequestParam Double maxPrice);

	@ApiOperation(value = "Get Company Coupons category", notes = "Get Company owned Coupons by category")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Coupon.class),
			@ApiResponse(code = 400, message = "Company Coupons not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-coupons-category", produces = "application/json")
	public ResponseEntity<?> getCompanyCoupons(
			@ApiParam(value = "Category type of the Coupon", required = true) @RequestParam CategoryUtil category);

	@ApiOperation(value = "Get Details", notes = "Returns the information of the Company",response = Company.class)
	@ApiResponse(code = 200, message = "Succesful operation", response = Company.class)
	@GetMapping(value = "/get-details", produces = "application/json")
	public ResponseEntity<?> getCompanyDetails();

}
