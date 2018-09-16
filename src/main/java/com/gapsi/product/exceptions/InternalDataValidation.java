package com.gapsi.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InternalDataValidation extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InternalDataValidation(String message) {
		super(message);
	}
}
