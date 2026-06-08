package com.lms.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
	
	private String message;
	private int StatusCode;
	private T data;

}

/*datatype of data should be generic
 *
 * ArrayList<Employee>
 * ApiResponse<Address>
 * Address data
 */

/*create BookController
 * IBookService
 * BookService
 * 
 * all 5 crud operation
 * 
 * save(give title and author)
 * 
 */

