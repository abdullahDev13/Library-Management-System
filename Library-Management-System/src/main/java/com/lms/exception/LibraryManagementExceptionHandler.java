package com.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lms.util.ApiResponse;

@ControllerAdvice  //help to recognize this class as exception handling class
public class LibraryManagementExceptionHandler {
	
	@ExceptionHandler(AddressIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleAddressIdNotFoundException(AddressIdNotFoundException exception)
	{
		ApiResponse<String> apiResponse= new ApiResponse<>();
		apiResponse.setMessage("Address for this id NotFound");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());  //400 ok
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleUserIdNotFoundException(UserIdNotFoundException exception)
	{
		ApiResponse<String> apiResponse= new ApiResponse<>();
		apiResponse.setMessage("User for this id NotFound");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());  //400 ok
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BookIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleBookIdNotFoundException(BookIdNotFoundException exception)
	{
		ApiResponse<String> apiResponse= new ApiResponse<>();
		apiResponse.setMessage("Book for this id not found");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.BAD_REQUEST);
	}

}
