package scrap.scrap.util.serviceUtil;

import com.jbc.exception.companyException.CompanyDuplicateValueException;

import lombok.SneakyThrows;

public interface ThrowUtil {

//	@SneakyThrows(CustomerDuplicateValueException.class)
	public default void throwCustomerDuplicateValueException() {
	//	throw new CustomerDuplicateValueException();
	}

	@SneakyThrows(CompanyDuplicateValueException.class)
	public default void throwCompanyDuplicateValueException(CompanyDuplicateValueException exception) {
		throw exception;
	}

}
