package com.banking.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourseNotFoundException(String resourseName, String fieldName, String fieldValue) {
		super(String.format("%s not found with given data %s : %s",resourseName,fieldName, fieldValue));
	}

}
