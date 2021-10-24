package com.jbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbc.exception.CustomException;
import com.jbc.message.request.user.CompanyRequest;
import com.jbc.message.request.user.CustomerRequest;
import com.jbc.message.response.ExceptionResponse;
import com.jbc.model.user.Company;
import com.jbc.model.user.Customer;
import com.jbc.service.LoginManagerServiceIfc;
import com.jbc.service.user.ifc.AdminServiceIfc;
import com.jbc.util.serviceUtil.UserTypeUtil;

/**
 * {@code Controller} used to handle methods from the
 * {@link com.jbc.service.LoginManagerServiceIfc} {@code Service}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see service#LoginManagerServiceIfc
 * @see user#User
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login-manager")
public class LoginManagerController implements LoginManagerControllerApi {

	/* attributes */
	@Autowired
	private LoginManagerServiceIfc loginManagerServ;
	@Autowired
	private AdminServiceIfc adminServ;

	@Override
	public ResponseEntity<?> login(String email, String password, UserTypeUtil userType) {
		try {
			return loginManagerServ.login(email, password, userType);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> registerCompany(CompanyRequest companyReq) {
		try {
			return new ResponseEntity<Company>(adminServ.createCompany(-1, companyReq.toCompany()), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> registerCustomer(CustomerRequest customerReq) {
		try {
			return new ResponseEntity<Customer>(adminServ.addCustomer(-1, customerReq.toCustomer()), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

}
