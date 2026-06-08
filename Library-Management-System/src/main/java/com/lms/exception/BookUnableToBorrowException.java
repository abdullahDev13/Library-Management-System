package com.lms.exception;


public class BookUnableToBorrowException extends RuntimeException{
	
	public BookUnableToBorrowException(String message)
	{
		super(message);
	}

}
