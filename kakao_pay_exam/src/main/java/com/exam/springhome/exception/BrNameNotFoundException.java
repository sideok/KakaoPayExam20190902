package com.exam.springhome.exception;
/**
 * 부점정보 미존재 Error 처리를 위한 Exception Class
 * @author SIDeok
 *
 */
public class BrNameNotFoundException extends RuntimeException{

	private String message;
	
    public BrNameNotFoundException(String message) {
    	this.message = message;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

