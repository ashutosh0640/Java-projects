package com.spring_boot.crud_operation.exception;

public class BadRequestException extends RuntimeException{
	
	public BadRequestException(String message) {
		super(message);
	}

}
