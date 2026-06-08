package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.util.ApiResponse;

public interface IAddressService {
	
	public ResponseEntity<ApiResponse<Address>> saveAddress(AddressDto addressDto);
	
	public ResponseEntity<ApiResponse<Address>> findAddressById(int addressId);
	
	public ResponseEntity<ApiResponse<Address>> updateCompleteAddress(AddressDto addressDto);
	
	public ResponseEntity<ApiResponse<Address>> updateAddressPartially(@RequestBody AddressDto addressDto, @PathVariable int addressId);
	
	public ResponseEntity<ApiResponse<Address>> deleteAddressbyId(@PathVariable int addressId);
	
	public ResponseEntity<ApiResponse<List<Address>>> fetchAllAddress();

}
