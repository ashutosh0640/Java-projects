package com.spring_boot.crud_operation.exception;

public class ConflictException extends RuntimeException{
	
	public ConflictException(String message) {
		super(message);
	}

}
