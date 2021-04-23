package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Map<String, String> error = new HashMap<>();

	public ValidationException(String msg) {
		super(msg);
	}

	public Map<String,String> getErrors() {
		return error;
	}
	public void addError(String fieldName, String errorMsg) {
		error.put(fieldName, errorMsg);
	}
}
