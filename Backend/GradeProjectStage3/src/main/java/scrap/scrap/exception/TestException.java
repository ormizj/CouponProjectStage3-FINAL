package scrap.scrap.exception;


import com.jbc.exception.CustomException;
import com.jbc.exception.couponException.CouponModifyNoStockException;
import com.jbc.exception.couponException.CouponNegativePriceException;

public class TestException {
	
	public static void main(String[] args) {
		try {
		CustomExceptionList exceptionList = new CustomExceptionList();
		
		exceptionList.addException(new CouponNegativePriceException(5));
		exceptionList.addException(new CouponNegativePriceException(5));
		exceptionList.addException(new CouponModifyNoStockException(5));
	
		
		throw exceptionList;
		} catch (CustomException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
	}

}
