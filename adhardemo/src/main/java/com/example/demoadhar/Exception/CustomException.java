package com.example.demoadhar.Exception;

public class CustomException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String msg;

	public CustomException(String msg) {
		this.msg = msg;
}
}