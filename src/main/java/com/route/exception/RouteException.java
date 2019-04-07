package com.route.exception;

/**
 * Application specific exceptions.
 * 
 * @author Arun G
 *
 */
public class RouteException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorCode;
	
	private String errorMessage;
	
	public RouteException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}
	
	public RouteException(String errorCode, Throwable throwable) {
		super(errorCode, throwable);
		this.errorCode = errorCode;
	}
	
	public RouteException(String errorCode, String message) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorMessage = message;
	}
	
	public RouteException(String errorCode, String message, Throwable throwable) {
		super(errorCode, throwable);
		this.errorCode = errorCode;
		this.errorMessage = message;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

}
