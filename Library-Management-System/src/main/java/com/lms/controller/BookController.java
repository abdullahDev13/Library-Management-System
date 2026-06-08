package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.BookDto;
import com.lms.dto.BookResponse;
import com.lms.entity.Book;
import com.lms.service.IBookService;
import com.lms.util.ApiResponse;

import jakarta.persistence.GeneratedValue;

@RestController
@RequestMapping("/book")
public class BookController 
{
	    @Autowired
	    IBookService bookService;
	
	    //http://localhost:8080/book
		@PostMapping
		public ResponseEntity<ApiResponse<Book>> saveBook(@RequestBody BookDto bookDto)
		{
			return bookService.saveBook(bookDto);
		}
		
		//http://localhost:8080/book/author/jk rowling
		@GetMapping("/author/{author}")
		public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthor(@PathVariable String author)
		{
			return bookService.displayBookByAuthor(author);
		}
		
		//http://localhost:8080/book/title/harryPotter2
		@GetMapping("/title/{title}")
		public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByTitle(@PathVariable String title)
		{
			return bookService.displayBookByTitle(title);
		}
		
		//http://localhost:8080/book/author/jk rowling/title/harryPotter1
		@GetMapping("author/{author}/title/{title}")
		public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthorAndTitle(@PathVariable String author, @PathVariable String title)
		{
			return bookService.displayBookByAuthorAndTitle(author, title);
		}

}
