package com.lms.serviceimplementation;

import com.lms.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.BookDto;
import com.lms.dto.BookResponse;
import com.lms.entity.Book;

import com.lms.repository.BookRepository;
import com.lms.service.IBookService;
import com.lms.util.ApiResponse;

@Service
public class BookService implements IBookService{
	
	private final UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	BookRepository bookRepository;

	BookService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public ResponseEntity<ApiResponse<Book>> saveBook(BookDto bookDto) 
	{
		Book book= modelMapper.map(bookDto, Book.class);
		bookRepository.save(book);
		//set response
		ApiResponse<Book> apiResponse= new ApiResponse<>();
		apiResponse.setMessage("Book Saved Successfully");
		apiResponse.setData(book);
		apiResponse.setStatusCode(HttpStatus.OK.value());  //200 ok
		return new ResponseEntity<ApiResponse<Book>>(apiResponse, HttpStatus.OK);
	}
	
	//filter option
	//by author
	//by title
	//by both author & title

	@Override
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthor(String author) 
	{
		List<Book> bookList= bookRepository.findByAuthor(author);
		
		//entity booklist has to be converted into book response list
		
		//create a list for  BookResponse
		List<BookResponse> bookResponseList= new ArrayList<>();
		
		for(Book book: bookList)
		{
			bookResponseList.add(modelMapper.map(book, BookResponse.class));
		}
		ApiResponse<List<BookResponse>> apiResponse= new ApiResponse<>();
		apiResponse.setData(bookResponseList);
		apiResponse.setMessage("Book fetched based on author Successfully");
		apiResponse.setStatusCode(HttpStatus.OK.value());  //200 ok
		return new ResponseEntity<ApiResponse<List<BookResponse>>>(apiResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByTitle(String title) 
	{
	    List<Book> bookList=  bookRepository.findByTitle(title);
	    
	    List<BookResponse> bookResponseList = new ArrayList<>();
	    
	    for(Book book : bookList)
	    {
	    	bookResponseList.add(modelMapper.map(book, BookResponse.class));
	    }
	    ApiResponse<List<BookResponse>> apiResponse= new ApiResponse<>();
	    apiResponse.setData(bookResponseList);
	    apiResponse.setMessage("Book fetched based on Title Successfully");
	    apiResponse.setStatusCode(HttpStatus.OK.value());
	    
	    return new ResponseEntity<ApiResponse<List<BookResponse>>>(apiResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthorAndTitle(String author, String title) 
	{
		List<Book> bookList= bookRepository.findByAuthorAndTitle(author, title);
		
		List<BookResponse> bookResponseList= new ArrayList<>();
		
		for(Book book: bookList)
		{
			bookResponseList.add(modelMapper.map(book, BookResponse.class));
		}
		
		ApiResponse<List<BookResponse>> apiResponse= new ApiResponse<>();
		apiResponse.setData(bookResponseList);
		apiResponse.setMessage("Book fetched based on Author and Title Successfully");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<ApiResponse<List<BookResponse>>>(apiResponse, HttpStatus.OK);
	}

}
