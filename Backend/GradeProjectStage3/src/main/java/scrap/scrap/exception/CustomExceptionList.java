package scrap.scrap.exception;

import java.util.ArrayList;
import java.util.List;

import com.jbc.exception.CustomException;

public class CustomExceptionList extends CustomException {

	private static final long serialVersionUID = 4139293387609694779L;
	private List<CustomException> exceptions;

	public CustomExceptionList() {
		exceptions = new ArrayList<>();
	}

	public void addException(CustomException exception) {
		exceptions.add(exception);
	}

	@Override
	public String toString() {
		String toString = "";
		for (CustomException customException : exceptions) {
			toString += customException.toString() + "\n";
		}
		return toString.substring(0,toString.length()-1);
	}

}
