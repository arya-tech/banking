package com.banking.accounts.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.banking.accounts.dto.ErrorResponseDto;

// whenever u r handling global exception u should mention below annotation

// if exception happened in any controller class to handle that , invoke the
					// method from this class.
@ControllerAdvice 
public class GlobalExceptionHandler {

	// the below annotation used to tell spring boot when this type of exception happened in controller then call this method.
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistException(
			CustomerAlreadyExistsException exception, WebRequest webRequest) {

		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
				exception.getMessage(), LocalDateTime.now());

		return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourseNotFoundException(
			ResourseNotFoundException exception, WebRequest webRequest) {

		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.NOT_FOUND,
				exception.getMessage(), LocalDateTime.now());

		return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.NOT_FOUND);

	}

}
