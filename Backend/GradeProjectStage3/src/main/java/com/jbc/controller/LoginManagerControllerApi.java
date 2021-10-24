package com.jbc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jbc.message.request.user.CompanyRequest;
import com.jbc.message.request.user.CustomerRequest;
import com.jbc.message.response.ExceptionResponse;
import com.jbc.message.response.JwtResponse;
import com.jbc.model.user.Company;
import com.jbc.model.user.Customer;
import com.jbc.util.serviceUtil.UserTypeUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Api for Swagger used to document methods from the
 * {@link com.jbc.service.LoginManagerServiceIfc} {@code Service}.
 * <p>
 * Used by the {@link com.jbc.controller.LoginManagerController}
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see controller.LoginManagerController
 * @see ifc#CustomerServiceIfc
 * @see user#User
 */
public interface LoginManagerControllerApi {

	/* methods */
	@ApiOperation(value = "Login", notes = "Login based on user type")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = JwtResponse.class),
			@ApiResponse(code = 400, message = "Login failed", response = ExceptionResponse.class) })
	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<?> login(@ApiParam(value = "Email of the user", required = true) @RequestParam String email,
			@ApiParam(value = "Password of the user", required = true) @RequestParam String password,
			@ApiParam(value = "Type of the user", required = true) @RequestParam UserTypeUtil userType);

	@ApiOperation(value = "Register Company", notes = "Registers a Company and returns it\n\n[id - unnecessary]")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Company.class),
			@ApiResponse(code = 400, message = "Company creation failed", response = ExceptionResponse.class) })
	@PostMapping(value = "/register-company", produces = "application/json")
	public ResponseEntity<?> registerCompany(@RequestBody CompanyRequest companyReq);

	@ApiOperation(value = "Register Customer", notes = "Registers a Customer and returns it\n\n[id - unnecessary]")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Customer.class),
			@ApiResponse(code = 400, message = "Customer addition failed", response = ExceptionResponse.class) })
	@PostMapping(value = "/register-customer", produces = "application/json")
	public ResponseEntity<?> registerCustomer(@RequestBody CustomerRequest customerReq);

}
