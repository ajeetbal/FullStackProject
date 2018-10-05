package com.mvc.mysql.exception;

public class BadRequestException extends Exception{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public BadRequestException() {
		super();
	}

	public BadRequestException(final String message) {
		super(message);
	}
}
