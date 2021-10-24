package com.jbc.controller.modelController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbc.controller.modelController.api.AdminControllerApi;
import com.jbc.exception.CustomException;
import com.jbc.message.request.user.CompanyRequest;
import com.jbc.message.request.user.CustomerRequest;
import com.jbc.message.response.ExceptionResponse;
import com.jbc.message.response.SuccessResponse;
import com.jbc.model.Coupon;
import com.jbc.model.Logger;
import com.jbc.model.user.Company;
import com.jbc.model.user.Customer;
import com.jbc.service.user.ifc.AdminServiceIfc;
import com.jbc.util.controllerUtil.TokenUtil;

/**
 * {@code Controller} used to handle methods from the
 * {@link com.jbc.service.user.ifc.AdminServiceIfc} {@code Service}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see ifc#AdminServiceIfc
 * @see controllerUtil#TokenUtil
 * @see user#User
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController implements AdminControllerApi, TokenUtil {

	/* attributes */
	@Autowired
	private AdminServiceIfc adminServ;

	/* methods */
	@Override
	public ResponseEntity<?> createCompany(CompanyRequest companyReq) {
		try {
			return new ResponseEntity<Company>(adminServ.createCompany(getUserId(), companyReq.toCompany()),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCompanies() {
		try {
			return new ResponseEntity<List<Company>>(adminServ.getCompanies(), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCompany(Long id) {
		try {
			return new ResponseEntity<Company>(adminServ.getCompany(id), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCompany(String email) {
		try {
			return new ResponseEntity<Company>(adminServ.getCompany(email), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> updateCompany(CompanyRequest companyReq) {
		try {
			return new ResponseEntity<Company>(adminServ.updateCompany(getUserId(), companyReq.toCompany()),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> deleteCompany(Long companyId) {
		try {
			adminServ.deleteCompany(getUserId(), companyId);
			return new ResponseEntity<SuccessResponse>(new SuccessResponse("Coupon deleted succesfully"),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> addCustomer(CustomerRequest customerReq) {
		try {
			return new ResponseEntity<Customer>(adminServ.addCustomer(getUserId(), customerReq.toCustomer()),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCustomers() {
		try {
			return new ResponseEntity<List<Customer>>(adminServ.getCustomers(), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCustomer(Long id) {
		try {
			return new ResponseEntity<Customer>(adminServ.getCustomer(id), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCustomer(String email) {
		try {
			return new ResponseEntity<Customer>(adminServ.getCustomer(email), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> updateCustomer(CustomerRequest customerReq) {
		try {
			return new ResponseEntity<Customer>(adminServ.updateCustomer(getUserId(), customerReq.toCustomer()),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> deleteCustomer(Long customerId) {
		try {
			adminServ.deleteCustomer(getUserId(), customerId);
			return new ResponseEntity<SuccessResponse>(new SuccessResponse("Coupon deleted succesfully"),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCoupons() {
		try {
			return new ResponseEntity<List<Coupon>>(adminServ.getCoupons(), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getLogs() {
		try {
			return new ResponseEntity<List<Logger>>(adminServ.getLogs(), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> clearLogs() {
		try {
			adminServ.clearLogs(getUserId());
			return new ResponseEntity<SuccessResponse>(new SuccessResponse("Coupon deleted succesfully"),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

}
