package com.lms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.lms.dto.UserDto;
import com.lms.entity.Book;
import com.lms.entity.User;
import com.lms.util.ApiResponse;

public interface IUserService {
	
	public ResponseEntity<ApiResponse<User>> saveUser(UserDto userDto, int addressId);
	
	public ResponseEntity<ApiResponse<User>> updateUserComplete(@RequestBody UserDto userDto);
	
	public ResponseEntity<ApiResponse<User>> updateUserPartially(@RequestBody UserDto userDto, @PathVariable int userId);
	
	public ResponseEntity<ApiResponse<User>> borrowBookByUser(int userId, int bookId);
	
	public ResponseEntity<ApiResponse<Book>> returnBookByUser(@PathVariable int bookId);

}
