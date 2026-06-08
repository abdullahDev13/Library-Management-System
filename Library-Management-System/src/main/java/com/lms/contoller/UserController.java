package com.lms.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.lms.dto.UserDto;
import com.lms.entity.Book;
import com.lms.entity.User;

import com.lms.service.IUserService;
import com.lms.util.ApiResponse;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	//http://localhost:8080/user/101
	@PostMapping("/{addressId}")
	public ResponseEntity<ApiResponse<User>> saveUser(@RequestBody UserDto userDto, @PathVariable int addressId)
	{
		return userService.saveUser(userDto, addressId);
	}

	//http://localhost:8080/user
	@PutMapping
	public ResponseEntity<ApiResponse<User>> updateUserComplete(@RequestBody UserDto userDto)
	{
		return userService.updateUserComplete(userDto);
	}
	
	//http://localhost:8080/user/1
	@PatchMapping("/{userId}")
	public ResponseEntity<ApiResponse<User>> updateUserPartially(@RequestBody UserDto userDto, @PathVariable int userId)
	{
		return userService.updateUserPartially(userDto, userId);
	}
	
	//http://localhost:8080/user/borrow/1/201
	@PutMapping("/borrow/{userId}/{bookId}")
	public ResponseEntity<ApiResponse<User>> borrowBookByUser(@PathVariable int userId, @PathVariable int bookId)
	{
		return userService.borrowBookByUser(userId, bookId);
	}
	
	//http://localhost:8080/user/return/201
	@PostMapping("/return/{bookId}")
	public ResponseEntity<ApiResponse<Book>> returnBookByUser(@PathVariable int bookId)
	{
		return userService.returnBookByUser(bookId);
	}
	

}
