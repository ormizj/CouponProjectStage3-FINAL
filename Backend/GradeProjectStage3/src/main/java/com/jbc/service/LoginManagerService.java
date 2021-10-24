package com.jbc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jbc.exception.loginException.InvalidLoginException;
import com.jbc.exception.loginException.MismatchLoginException;
import com.jbc.exception.loginException.NullLoginException;
import com.jbc.message.response.JwtResponse;
import com.jbc.model.Logger;
import com.jbc.repository.LoggerRepository;
import com.jbc.security.jwt.JwtProvider;
import com.jbc.security.services.UserPrinciple;
import com.jbc.service.user.CompanyService;
import com.jbc.service.user.CustomerService;
import com.jbc.util.modelUtil.ModelEntityUtil;
import com.jbc.util.serviceUtil.ModelActionUtil;
import com.jbc.util.serviceUtil.UserTypeUtil;

/**
 * {@code Service} that {@code implements}
 * {@link com.jbc.service.LoginManagerServiceIfc}, and manages the login system,
 * and retrieving corresponding Jwt tokens based on the {@code email},
 * {@code password} and {@code userType}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see service#LoginManagerServiceIfc
 */
@Service
public class LoginManagerService implements LoginManagerServiceIfc {

	/* attributes */
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private CompanyService companyServ;
	@Autowired
	private CustomerService customerServ;
	@Autowired
	private LoggerRepository loggerRepo;

	@Override
	public ResponseEntity<?> login(String email, String password, UserTypeUtil userType)
			throws InvalidLoginException, NullLoginException, MismatchLoginException {
		if (email == null || password == null || userType == null)
			throw new NullLoginException();

		Authentication authentication = null;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (Exception e) {
			if (companyServ.companyExists(email) || customerServ.customerExists(email)) {
				throw new InvalidLoginException(email, true, userType);
			}
			throw new InvalidLoginException(email, false, userType);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwtToken(authentication);
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

		Logger logger = new Logger(-1, null, userPrinciple.getId(), null, ModelActionUtil.LOGIN, null, null);
		Collection<? extends GrantedAuthority> authorities = null;
		switch (userType) {
		case ADMIN:
			if (userPrinciple.getAuthorities().contains(new SimpleGrantedAuthority(UserTypeUtil.ADMIN.name()))) {
				authorities = new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(UserTypeUtil.ADMIN.name())));
				logger.setEntity(ModelEntityUtil.ADMIN);
			}
			break;
		case COMPANY:
			if (userPrinciple.getAuthorities().contains(new SimpleGrantedAuthority(UserTypeUtil.COMPANY.name()))) {
				authorities = new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(UserTypeUtil.COMPANY.name())));
				logger.setEntity(ModelEntityUtil.COMPANY);
			}
			break;
		case CUSTOMER:
			if (userPrinciple.getAuthorities().contains(new SimpleGrantedAuthority(UserTypeUtil.CUSTOMER.name()))) {
				authorities = new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(UserTypeUtil.CUSTOMER.name())));
				logger.setEntity(ModelEntityUtil.CUSTOMER);
			}
			break;
		default:
			break;
		}
		if (authorities == null) {
			throw new MismatchLoginException();
		}
		loggerRepo.save(logger);
		return ResponseEntity.ok(new JwtResponse(jwt, userPrinciple.getUsername(), authorities));
	}

}
