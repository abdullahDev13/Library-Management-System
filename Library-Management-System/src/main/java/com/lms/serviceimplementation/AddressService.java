package com.lms.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.exception.AddressIdNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.service.IAddressService;
import com.lms.util.ApiResponse;

@Service
public class AddressService implements IAddressService{

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public ResponseEntity<ApiResponse<Address>> saveAddress(AddressDto addressDto) {
		Address address= modelMapper.map(addressDto, Address.class);
		addressRepository.save(address);
		//set response
		ApiResponse<Address> apiResponse= new ApiResponse<>();
		apiResponse.setMessage("Address Saved Successfully");
		apiResponse.setData(address);
		apiResponse.setStatusCode(HttpStatus.OK.value());  //200 ok
		return new ResponseEntity<ApiResponse<Address>>(apiResponse, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ApiResponse<Address>> findAddressById(int addressId)
	{
		Optional<Address> optional= addressRepository.findById(addressId);
		ApiResponse<Address> apiResponse= new ApiResponse<>();
		if(optional.isPresent())
		{
			Address address= optional.get();
			apiResponse.setMessage("Address Found Successfully");
			apiResponse.setData(address);
			apiResponse.setStatusCode(HttpStatus.OK.value());  //200 ok
			return new ResponseEntity<ApiResponse<Address>>(apiResponse, HttpStatus.OK);
		}
		else
		{
//			apiResponse.setMessage("Address for this id NotFound");
//			apiResponse.setData(null);
//			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());  //400 ok
//			return new ResponseEntity<ApiResponse<Address>>(apiResponse, HttpStatus.BAD_REQUEST);
			
			throw new AddressIdNotFoundException("Invalid Address Id");
		}
	}

	@Override
	public ResponseEntity<ApiResponse<Address>> updateCompleteAddress(AddressDto addressDto) 
	{
		Address address= modelMapper.map(addressDto, Address.class);
		addressRepository.save(address);
		
		ApiResponse<Address> apiResponse= new ApiResponse<>();
		apiResponse.setMessage("Address updated Compeletely");
		apiResponse.setData(address);
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<ApiResponse<Address>>(apiResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<Address>> updateAddressPartially(AddressDto addressDto, int addressId) 
	{
		Optional<Address> optional=  addressRepository.findById(addressId);
		
		if(optional.isPresent())
		{
			Address address= optional.get();
			
			//we are changing address city name
			address.setCity(addressDto.getCity());
			addressRepository.save(address);
			
			ApiResponse<Address> apiResponse= new ApiResponse<>();
			apiResponse.setMessage("update city name");
			apiResponse.setData(address);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ApiResponse<Address>>(apiResponse, HttpStatus.OK);
		}
		else
		{
			throw new AddressIdNotFoundException("Invalid AddressId ") ;
		}
	}

	@Override
	public ResponseEntity<ApiResponse<Address>> deleteAddressbyId(int addressId) 
	{
		Optional<Address> optional= addressRepository.findById(addressId);
		
		if(optional.isPresent())
		{
			Address address= optional.get();
			addressRepository.delete(address);
			
			ApiResponse<Address> apiResponse= new ApiResponse<>();
			apiResponse.setMessage("Address deleted Successfully");
			apiResponse.setData(address);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ApiResponse<Address>>(apiResponse, HttpStatus.OK);
		}
		else
		{
			throw new AddressIdNotFoundException("Invalid AddressId");
		}
	}

	@Override
	public ResponseEntity<ApiResponse<List<Address>>> fetchAllAddress() 
	{
		    List<Address> addressList= addressRepository.findAll();
		
			ApiResponse<List<Address>> apiResponse= new  ApiResponse<>();
			apiResponse.setMessage("Address Found");
			apiResponse.setData(addressList);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ApiResponse<List<Address>>>(apiResponse, HttpStatus.OK);
		

	}

}
