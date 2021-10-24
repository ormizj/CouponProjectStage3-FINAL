package com.jbc.controller.modelController.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jbc.message.request.user.CompanyRequest;
import com.jbc.message.request.user.CustomerRequest;
import com.jbc.message.response.ExceptionResponse;
import com.jbc.message.response.SuccessResponse;
import com.jbc.model.Coupon;
import com.jbc.model.Logger;
import com.jbc.model.user.Company;
import com.jbc.model.user.Customer;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Api for Swagger used to document methods from the
 * {@link com.jbc.service.user.ifc.AdminServiceIfc} {@code Service}.
 * <p>
 * Used by the {@link com.jbc.controller.modelController.AdminController}
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see modelController.AdminController
 * @see ifc#UserServiceIfc
 * @see user#User
 */
public interface AdminControllerApi {

	/* methods */
	@ApiOperation(value = "Create Company", notes = "Creates a Company and returns it\n\n[id - unnecessary]")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Company.class),
			@ApiResponse(code = 400, message = "Company creation failed", response = ExceptionResponse.class) })
	@PostMapping(value = "/create-company", produces = "application/json")
	public ResponseEntity<?> createCompany(@RequestBody CompanyRequest companyReq);

	@ApiOperation(value = "Get Companies", notes = "Get all Companies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Company.class),
			@ApiResponse(code = 400, message = "No Companies found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-companies", produces = "application/json")
	public ResponseEntity<?> getCompanies();

	@ApiOperation(value = "Get Company", notes = "Get Company by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Company.class),
			@ApiResponse(code = 400, message = "Company not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-company", produces = "application/json")
	public ResponseEntity<?> getCompany(
			@ApiParam(value = "ID of the Company to return", required = true, example = "0") @RequestParam Long id);

	@ApiOperation(value = "Get Company email", notes = "Get Company by email")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Company.class),
			@ApiResponse(code = 400, message = "Company not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-company-email", produces = "application/json")
	public ResponseEntity<?> getCompany(
			@ApiParam(value = "Email of the Company to return", required = true) @RequestParam String email);

	@ApiOperation(value = "Update Company", notes = "Updates a Company and returns it")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Company.class),
			@ApiResponse(code = 400, message = "Company update failed", response = ExceptionResponse.class) })
	@PutMapping(value = "/update-company", produces = "application/json")
	public ResponseEntity<?> updateCompany(@RequestBody CompanyRequest companyReq);

	@ApiOperation(value = "Delete Company", notes = "Deletes a Company and his Coupons")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = SuccessResponse.class),
			@ApiResponse(code = 400, message = "Company deletion failed", response = ExceptionResponse.class) })
	@DeleteMapping(value = "/delete-company", produces = "application/json")
	public ResponseEntity<?> deleteCompany(
			@ApiParam(value = "ID of the Company to delete", required = true, example = "0") @RequestParam Long companyId);

	@ApiOperation(value = "Add Customer", notes = "Adds a Customer and returns it\n\n[id - unnecessary]")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Customer.class),
			@ApiResponse(code = 400, message = "Customer addition failed", response = ExceptionResponse.class) })
	@PostMapping(value = "/add-customer", produces = "application/json")
	public ResponseEntity<?> addCustomer(@RequestBody CustomerRequest customerReq);

	@ApiOperation(value = "Get Customers", notes = "Get all Customers")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Customer.class),
			@ApiResponse(code = 400, message = "No Customers found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-customers", produces = "application/json")
	public ResponseEntity<?> getCustomers();

	@ApiOperation(value = "Get Customer", notes = "Get Customer by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Customer.class),
			@ApiResponse(code = 400, message = "Customer not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-customer", produces = "application/json")
	public ResponseEntity<?> getCustomer(
			@ApiParam(value = "ID of the Customer to return", required = true, example = "0") @RequestParam Long id);

	@ApiOperation(value = "Get Customer email", notes = "Get Customer by email")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Customer.class),
			@ApiResponse(code = 400, message = "Customer not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-customer-email", produces = "application/json")
	public ResponseEntity<?> getCustomer(
			@ApiParam(value = "Email of the Customer to return", required = true) @RequestParam String email);

	@ApiOperation(value = "Update Customer", notes = "Updates a Customer and returns it")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Customer.class),
			@ApiResponse(code = 400, message = "Customer update failed", response = ExceptionResponse.class) })
	@PutMapping(value = "/update-customer", produces = "application/json")
	public ResponseEntity<?> updateCustomer(@RequestBody CustomerRequest customerReq);

	@ApiOperation(value = "Delete Customer", notes = "Deletes a Customer")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = SuccessResponse.class),
			@ApiResponse(code = 400, message = "Customer deletion failed", response = ExceptionResponse.class) })
	@DeleteMapping(value = "/delete-customer", produces = "application/json")
	public ResponseEntity<?> deleteCustomer(
			@ApiParam(value = "ID of the Customer to delete", required = true, example = "0") @RequestParam Long customerId);

	@ApiOperation(value = "Get Coupons", notes = "Get all Coupons")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Coupon.class),
			@ApiResponse(code = 400, message = "No Coupons found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-coupons", produces = "application/json")
	public ResponseEntity<?> getCoupons();

	@ApiOperation(value = "Get Logs", notes = "Get All Logs")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = Logger.class),
			@ApiResponse(code = 400, message = "No Logs found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-logs", produces = "application/json")
	public ResponseEntity<?> getLogs();

	@ApiOperation(value = "Clear Logs", notes = "Deletes all Logs")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful operation", response = SuccessResponse.class),
			@ApiResponse(code = 400, message = "Logs deletion failed", response = ExceptionResponse.class) })
	@DeleteMapping(value = "/clear-logs", produces = "application/json")
	public ResponseEntity<?> clearLogs();

}