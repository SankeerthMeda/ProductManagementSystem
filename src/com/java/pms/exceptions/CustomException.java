package com.java.pms.exceptions;

@SuppressWarnings("serial")
public class CustomException extends Exception{
//	private static final long serialVersionUID = 1L;

	public CustomException(String ex) {
		super(ex);
	}
}
