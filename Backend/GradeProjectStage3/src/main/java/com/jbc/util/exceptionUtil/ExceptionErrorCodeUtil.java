package com.jbc.util.exceptionUtil;

/**
 * {@code enum} which contains the {@code errorCode} of every {@link com.jbc.exception.CustomException}.
 * <p>
 * <li>The method {@code toDouble} should be used after specifying which
 * information is wanted.</li>
 * <p>
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 * @see #toString()
 */
public enum ExceptionErrorCodeUtil {
	
	CustomExceptionList("LST-000.000"),

	CompanyAlreadyExistsException("CMP-001.001"), CompanyDuplicateValueException("CMP-001.002"), CompanyIsEmptyException("CMP-001.003"),
	CompanyIsNullException("CMP-001.004"), CompanyNotFoundException("CMP-001.005"), CompanyNullValueException("CMP-001.006"),

	CouponAlreadyExistsException("CPN-002.001"), CouponDuplicateValueException("CPN-002.002"),
	CouponExpiredModifyException("CPN-002.003"), CouponExpiredPurchaseException("CPN-002.004"), CouponIsEmptyException("CPN-002.005"),
	CouponIsNullException("CPN-002.006"), CouponModifyNoStockException("CPN-002.007"), CouponNegativePriceException("CPN-002.008"),
	CouponNotFoundException("CPN-002.009"), CouponNullValueException("CPN-002.010"), CouponOwnedException("CPN-002.011"),
	CouponPurchaseNoStockException("CPN-002.012"),

	CustomerAlreadyExistsException("CST-003.001"), CustomerDuplicateValueException("CST-003.002"),
	CustomerIsEmptyException("CST-003.003"), CustomerIsNullException("CST-003.004"), CustomerNotFoundException("CST-003.005"),
	CustomerNullValueException("CST-003.006"),
	
	LoggerIsEmptyException("LGR-004.001"),
	
	InvalidLoginException("LGN-005.001"),NullLoginException("LGN-005.002"),MismatchLoginException("LGN-005.003"),
	
	UserEmailAlreadyExists("USR-006.001");

	private final String ERROR_CODE;

	ExceptionErrorCodeUtil(String errorCode) {
		ERROR_CODE = errorCode;
	}

	@Override
	public String toString() {
		return ERROR_CODE;
	}

}
