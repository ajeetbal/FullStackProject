package com.mvc.mysql.exception;

public class InternalServerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InternalServerException() {
		super();
	}

	public InternalServerException(final String message) {
		super(message);
	}
}
