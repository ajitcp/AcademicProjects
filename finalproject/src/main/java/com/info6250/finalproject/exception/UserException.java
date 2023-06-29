package com.info6250.finalproject.exception;

public class UserException extends Exception {
	
	public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

}
