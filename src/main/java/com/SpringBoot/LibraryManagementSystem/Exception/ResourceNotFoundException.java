package com.SpringBoot.LibraryManagementSystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private long fieldValue;
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super();
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	public ResourceNotFoundException(String message) {
		super(message);
	}
	public ResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public String getResourceName() {
		return resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public long getFieldValue() {
		return fieldValue;
	}
	
}
