package com.pe.client_management.exception;

public class BadRequestException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 */
	public BadRequestException(String msg) {
		super(msg);
	}

	/**
	 * @param mensaje
	 * @param exception
	 */
	public BadRequestException(String mensaje, Throwable exception) {
		super(mensaje);
	}

}
