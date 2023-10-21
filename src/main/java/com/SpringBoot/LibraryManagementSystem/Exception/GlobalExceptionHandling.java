package com.SpringBoot.LibraryManagementSystem.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandling extends RuntimeException {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorInformation> handleResourceNotFoundException(ResourceNotFoundException exception,WebRequest req){
	ErrorInformation errorDetails = new ErrorInformation(new Date(), exception.getMessage(), req.getDescription(false));
	return new ResponseEntity<ErrorInformation>(errorDetails, HttpStatus.NOT_FOUND);	 
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInformation> handleGlobalException(Exception exception,WebRequest req){
	ErrorInformation errorDetails = new ErrorInformation(new Date(), exception.getMessage(), req.getDescription(false));
	return new ResponseEntity<ErrorInformation>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
