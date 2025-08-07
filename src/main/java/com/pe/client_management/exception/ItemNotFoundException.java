package com.pe.client_management.exception;

public class ItemNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 */
	public ItemNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * @param mensaje
	 * @param exception
	 */
	public ItemNotFoundException(String mensaje, Throwable exception) {
		super(mensaje);
	}

}
