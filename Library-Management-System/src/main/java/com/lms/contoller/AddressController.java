package com.lms.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.service.IAddressService;
import com.lms.util.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/*
 * @Operation : used to specify what operation that particular api(method) does
 * @ApiResponse : used to specify what response that particular api gives
 */

@RestController
//common url
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	IAddressService addressService;
	
	//http://localhost:8080/address
	
	@Operation(operationId= "CreateAddress", summary= "Adding Address",
			   description= "This rest end point is used to create and add one address")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode= "200",
	           description= "creates and return address entity")})
	@PostMapping
	public ResponseEntity<ApiResponse<Address>> saveAddress(@RequestBody AddressDto addressDto)
	{
		return addressService.saveAddress(addressDto);
	}
	
	//http://localhost:8080/address/101
	
	@Operation(operationId= "FetchAddress", summary= "Fetch One Address",
			   description= "This rest end point is used to fetch one address using Address id")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode= "200",
	           description= "fetch address using id and return address entity")
	           ,@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode= "400",
	           description= "fetching address using id")})
	@GetMapping("{addressId}")
	public ResponseEntity<ApiResponse<Address>> findAddressById(@PathVariable int addressId)
	{
		return addressService.findAddressById(addressId);
	}
	
	//http://localhost:8080/address
	@PutMapping
	public ResponseEntity<ApiResponse<Address>> updateCompleteAddress(@RequestBody AddressDto addressDto )
	{
		return addressService.updateCompleteAddress(addressDto);
	}
	
	//http://localhost:8080/address/102
	@PatchMapping("{addressId}")
	public ResponseEntity<ApiResponse<Address>> updateAddressPartially(@RequestBody AddressDto addressDto, @PathVariable int addressId)
	{
		return addressService.updateAddressPartially(addressDto, addressId);
	}
	
	@DeleteMapping("{addressId}")
	public ResponseEntity<ApiResponse<Address>> deleteAddressbyId(@PathVariable int addressId)
	{
		return addressService.deleteAddressbyId(addressId);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<Address>>> fetchAllAddress()
	{
		return addressService.fetchAllAddress();
	}
	
   /* complete
    * updateAddress()
    * deleteAddressById
    * fetchAllAddress
    */

}
