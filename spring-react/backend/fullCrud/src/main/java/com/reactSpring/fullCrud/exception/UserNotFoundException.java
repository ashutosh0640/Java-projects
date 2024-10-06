package com.reactSpring.fullCrud.exception;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(String message) {
		super(message);
	}

}
