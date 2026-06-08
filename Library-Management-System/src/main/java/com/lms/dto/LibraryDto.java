package com.lms.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibraryDto {
	
	private int libraryId;
	private String libraryName;
	private long phoneNumber;

}
