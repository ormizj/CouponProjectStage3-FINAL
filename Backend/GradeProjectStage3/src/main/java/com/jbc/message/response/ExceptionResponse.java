package com.jbc.message.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * {@link com.jbc.exception.CustomException} type {@code class} used for
 * {@code CustomException} responses.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 */
@Getter
@Setter
@AllArgsConstructor
@ApiModel(description = "Throws exceptions, for unsuccessful responses.\n\n"
		+ "Exception List:\r\n\n"
		+ "exception:\r\n"
		+ "CustomExceptionList - LST-000.000\r\n"
		+ "\r\n"
		+ "companyException:\r\n"
		+ "CompanyAlreadyExistsException - CMP-001.001\r\n"
		+ "CompanyDuplicateValueException - CMP-001.002\r\n"
		+ "CompanyIsEmptyException - CMP-001.003\r\n"
		+ "CompanyIsNullException - CMP-001.004\r\n"
		+ "CompanyNotFoundException - CMP-001.005\r\n"
		+ "CompanyNullValueException - CMP-001.006\r\n"
		+ "\r\n"
		+ "couponException:\r\n"
		+ "CouponAlreadyExistsException - CPN-002.001\r\n"
		+ "CouponDuplicateValueException - CPN-002.002\r\n"
		+ "CouponExpiredModifyException - CPN-002.003\r\n"
		+ "CouponExpiredPurchaseException - CPN-002.004\r\n"
		+ "CouponIsEmptyException - CPN-002.005\r\n"
		+ "CouponIsNullException - CPN-002.006\r\n"
		+ "CouponModifyNoStockException - CPN-002.007\r\n"
		+ "CouponNegativePriceException - CPN-002.008\r\n"
		+ "CouponNotFoundException - CPN-002.009\r\n"
		+ "CouponNullValueException - CPN-002.010\r\n"
		+ "CouponOwnedException - CPN-002.011\r\n"
		+ "CouponPurchaseNoStockException - CPN-002.012\r\n"
		+ "\r\n"
		+ "customerException:\r\n"
		+ "CustomerAlreadyExistsException - CST-003.001\r\n"
		+ "CustomerDuplicateValueException- CST-003.002\r\n"
		+ "CustomerIsEmptyException - CST-003.003\r\n"
		+ "CustomerIsNullException - CST-003.004\r\n"
		+ "CustomerNotFoundException - CST-003.005\r\n"
		+ "CustomerNullValueException - CST-003.006\r\n"
		+ "\r\n"
		+ "loggerException:\r\n"
		+ "LoggerIsEmptyException - LGR-004.001\r\n"
		+ "\r\n"
		+ "loginException:\r\n"
		+ "InvalidLoginException - LGN-005.001\r\n"
		+ "NullLoginException - LGN-005.002\r\n"
		+ "MismatchLoginException - LGN-005.003\r\n"
		+ "\r\n"
		+ "UserEmailAlreadyExists - USR-006.001"
	)
public class ExceptionResponse {
	
	/* attributes */
	@ApiModelProperty(value = "Exception message.")
	private String response;
	@ApiModelProperty(value = "Exception error code.", example = "CMP-001.001")
	private String errorCode;

}