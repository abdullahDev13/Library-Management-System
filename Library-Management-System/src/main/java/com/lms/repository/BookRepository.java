package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	
	//create customized methods
	//start from findByNameOfDataMember()
	
	List<Book> findByAuthor(String author);
	List<Book> findByTitle(String title);
	List<Book> findByAuthorAndTitle(String author, String title);

}
