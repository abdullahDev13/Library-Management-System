package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.lms.dto.BookDto;
import com.lms.dto.BookResponse;
import com.lms.entity.Book;
import com.lms.util.ApiResponse;

public interface IBookService {
	
	public ResponseEntity<ApiResponse<Book>> saveBook(BookDto bookDto);
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthor(String author);
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByTitle(String title);
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthorAndTitle(@PathVariable String author, @PathVariable String title);
}
