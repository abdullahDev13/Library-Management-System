package com.lms.service;

import org.springframework.http.ResponseEntity;

import com.lms.dto.LibraryDto;
import com.lms.entity.Library;
import com.lms.util.ApiResponse;

public interface ILibraryService {
	
	public ResponseEntity<ApiResponse<Library>> saveLibrary(LibraryDto libraryDto, int addressId);
	public ResponseEntity<ApiResponse<Library>> addBookToLibrary(int libraryId, int bookId);

}
