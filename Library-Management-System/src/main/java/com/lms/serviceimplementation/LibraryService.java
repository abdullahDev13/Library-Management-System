package com.lms.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.LibraryDto;
import com.lms.entity.Address;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.exception.AddressIdNotFoundException;
import com.lms.exception.BookUnableToAddToLibraryException;
import com.lms.repository.AddressRepository;
import com.lms.repository.BookRepository;
import com.lms.repository.LibraryRepository;
import com.lms.service.ILibraryService;
import com.lms.util.ApiResponse;

@Service
public class LibraryService implements ILibraryService{

	@Autowired
	LibraryRepository libraryRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	BookRepository bookRepository;
	
	@Override
	public ResponseEntity<ApiResponse<Library>> saveLibrary(LibraryDto libraryDto, int addressId) {
		
		//convert dto to entity
		Library library= modelMapper.map(libraryDto, Library.class);
		//fetch address
		Optional<Address> optionalAddress= addressRepository.findById(addressId);
		ApiResponse<Library> apiResponse= new ApiResponse<>();
		if(optionalAddress.isPresent())
		{
			//set address to Library
			Address address= optionalAddress.get();
			library.setAddress(address);
			//save library
			libraryRepository.save(library);
			apiResponse.setMessage("Library Saved Successfully");
			apiResponse.setData(library);
			apiResponse.setStatusCode(HttpStatus.OK.value());  //200 ok
			return new ResponseEntity<ApiResponse<Library>>(apiResponse, HttpStatus.OK);
		}
		else
		{
//			apiResponse.setMessage("Address for this id NotFound, Library Not Saved");
//			apiResponse.setData(null);
//			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());  //400 ok
//			return new ResponseEntity<ApiResponse<Library>>(apiResponse, HttpStatus.BAD_REQUEST);
			
			throw new AddressIdNotFoundException("Invalid Address Id");
		}
		
		
	}

	@Override
	public ResponseEntity<ApiResponse<Library>> addBookToLibrary(int libraryId, int bookId) 
	{
		//fetch both objects using their ids
		Optional<Library> optionalLibrary= libraryRepository.findById(libraryId);
		Optional<Book> optionalBook= bookRepository.findById(bookId);
		
		ApiResponse<Library> apiResponse= new ApiResponse<>();
		//validate both
		if(optionalLibrary.isPresent() && optionalBook.isPresent())
		{
			//fetch entity objects from optional
			Library library= optionalLibrary.get();
			Book book= optionalBook.get();
			
			//fetch list of Books from library
			List<Book> bookList= library.getBookList();
			
			if(bookList==null)
			{
				//new List
				bookList= new ArrayList<>();
			}
			
			//add book object to list
			bookList.add(book);
			//set list to Library
			library.setBookList(bookList);
			//update library
			libraryRepository.save(library);
			apiResponse.setMessage("Book added to library Successfully");
			apiResponse.setData(library);
			apiResponse.setStatusCode(HttpStatus.OK.value());  //200 ok
			return new ResponseEntity<ApiResponse<Library>>(apiResponse, HttpStatus.OK);
		}
		
		else
		{
			throw new BookUnableToAddToLibraryException("libraryId/bookId not available");
		}
	}

}
