package com.lms.serviceimplementation;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.AddressDto;
import com.lms.dto.UserDto;
import com.lms.entity.Address;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.entity.User;
import com.lms.exception.AddressIdNotFoundException;
import com.lms.exception.BookIdNotFoundException;
import com.lms.exception.BookUnableToBorrowException;
import com.lms.exception.UserIdNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.repository.BookRepository;
import com.lms.repository.UserRepository;
import com.lms.service.IUserService;
import com.lms.util.ApiResponse;

@Service
public class UserService implements IUserService{

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BookRepository bookRepository;
	
	@Override
	public ResponseEntity<ApiResponse<User>> saveUser(UserDto userDto, int addressId) 
	{
			User user= modelMapper.map(userDto, User.class);
			Optional<Address> optionalAddress= addressRepository.findById(addressId);
			ApiResponse<User> apiResponse= new ApiResponse<>();
			if(optionalAddress.isPresent())
			{
				user.setAddress(optionalAddress.get());
				userRepository.save(user);
				apiResponse.setMessage("User Saved Successfully");
				apiResponse.setData(user);
				apiResponse.setStatusCode(HttpStatus.OK.value());  //200 ok
				return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
			}
			else
			{
//				apiResponse.setMessage("Address for this id NotFound, Library Not Saved");
//				apiResponse.setData(null);
//				apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());  //400 ok
//				return new ResponseEntity<ApiResponse<Library>>(apiResponse, HttpStatus.BAD_REQUEST);
				
				throw new AddressIdNotFoundException("Invalid Address Id");
			}
	}
	
	/*
	 * complete update: entire object including id-@PutMapping
	 * partial update: particular field :@PatchMApping
	 */

	@Override
	public ResponseEntity<ApiResponse<User>> updateUserComplete(UserDto userDto) 
	{
		User user= modelMapper.map(userDto, User.class);
		userRepository.save(user);
		ApiResponse<User> apiResponse= new ApiResponse<>();
		apiResponse.setMessage("User Updated Successfully");
		apiResponse.setData(user);
		apiResponse.setStatusCode(HttpStatus.OK.value());  //200 ok
		return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<User>> updateUserPartially(UserDto userDto, int userId) 
	{
		//using id fetch old user object
		Optional<User> optionalUser= userRepository.findById(userId);
		ApiResponse<User> apiResponse= new ApiResponse<>();
		if(optionalUser.isPresent())
		{
			//separate user from optional
			User user= optionalUser.get();
			//we are updating name
			//for old user set new name
			user.setUserName(userDto.getUserName());
			//update old user
			userRepository.save(user);
			apiResponse.setMessage("User Updated Successfully");
			apiResponse.setData(user);
			apiResponse.setStatusCode(HttpStatus.OK.value());  //200 ok
			return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
		}
		
		else
		{
			throw new UserIdNotFoundException("Invalid User Id");
		}
		
	}

	@Override
	public ResponseEntity<ApiResponse<User>> borrowBookByUser(int userId, int bookId) 
	{
		Optional<User> optionalUser= userRepository.findById(userId);
		Optional<Book> optionalBook= bookRepository.findById(bookId);
		
		//1.book is present 2.user is present 3.borrowed should be false
		if(optionalBook.isPresent() && optionalUser.isPresent() && !optionalBook.get().isBorrowed())
		{
			User user= optionalUser.get();
			Book book= optionalBook.get();
			
			//set book to user
			book.setUers(user);
			book.setBorrowedTime(LocalDateTime.now());
			book.setBorrowed(true);
			
			//update book
			bookRepository.save(book);
			ApiResponse<User> apiResponse= new ApiResponse<>();
			apiResponse.setMessage("User Borrowed Book Successfully");
			apiResponse.setData(user);
			apiResponse.setStatusCode(HttpStatus.OK.value());  //200 ok
			return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
		}
		else
		{
			throw new BookUnableToBorrowException("invalid bookId/userId or book is already borrowed");
		}
		
	}

	@Override
	public ResponseEntity<ApiResponse<Book>> returnBookByUser(int bookId) 
	{
		Optional<Book> optionalBook= bookRepository.findById(bookId);
		
		if(optionalBook.isPresent())
		{
			Book book=  optionalBook.get();
			book.setUers(null);
			book.setBorrowed(false);
			book.setReturntime(LocalDateTime.now());
			bookRepository.save(book);
			
			ApiResponse<Book> apiResponse= new ApiResponse<>();
			apiResponse.setData(book);
			apiResponse.setMessage("Book Returned Successfully");
			apiResponse.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ApiResponse<Book>> (apiResponse, HttpStatus.OK); 
			
		}
		else
		{
			throw new BookIdNotFoundException("Invalid bookId");
		}
	}
	
	/*
	 * to returnBook
	 * take (bookId) from postman
	 * fetch book, validate
	 * setUser(null)
	 * setBorrowed(false)
	 * setReturnTime()
	 * update book 	
	 */

}
