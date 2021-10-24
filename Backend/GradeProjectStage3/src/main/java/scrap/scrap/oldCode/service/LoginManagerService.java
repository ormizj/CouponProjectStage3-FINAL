package scrap.scrap.oldCode.service;

//public class LoginManagerService implements LoginManagerServiceIfc {
//
//	/* attributes */
//	@Autowired
//	private AdminServiceIfc adminServ;
//	@Autowired
//	private CompanyServiceIfc companyServ;
//	@Autowired
//	private CustomerServiceIfc customerServ;
//
//	@Override
//	public User login(String email, String password, UserTypeUtil userType)
//			throws InvalidLoginException, NullLoginException, CustomerNotFoundException, CompanyNotFoundException {
//		if (email == null || password == null || userType == null)
//			throw new NullLoginException();
//		boolean emailExists = false;
//		switch (userType) {
//		case ADMIN:
//			Optional<User> adminOption = adminServ.login(email, password);
//			if (adminOption.isPresent())
//				return adminOption.get();
//			break;
//		case COMPANY:
//			if (companyServ.login(email, password))
//				return adminServ.getCompany(email);
//			emailExists = companyServ.companyExists(email);
//			break;
//		case CUSTOMER:
//			if (customerServ.login(email, password))
//				return adminServ.getCustomer(email);
//			emailExists = customerServ.customerExists(email);
//			break;
//		default:
//			break;
//		}
//		throw new InvalidLoginException(email, emailExists, userType);
//	}
//
//}
