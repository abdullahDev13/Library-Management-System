package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.LibraryDto;
import com.lms.entity.Library;
import com.lms.service.ILibraryService;
import com.lms.util.ApiResponse;

@RestController
@RequestMapping("/library")
public class LibraryController {
	
	@Autowired
	ILibraryService libraryService;
	
	//http://localhost:8080/library/101
	@PostMapping("/{addressId}")
	public ResponseEntity<ApiResponse<Library>> saveLibrary(@RequestBody LibraryDto libraryDto, @PathVariable int addressId)
	{
		return libraryService.saveLibrary(libraryDto, addressId);
	}
	
	//http://localhost:8080/library/301/201
	@PutMapping("/{libraryId}/{bookId}")
	public ResponseEntity<ApiResponse<Library>> addBookToLibrary(@PathVariable int libraryId, @PathVariable int bookId)
	{
		return libraryService.addBookToLibrary(libraryId, bookId);
	}

}
