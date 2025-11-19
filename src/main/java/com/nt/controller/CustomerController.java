package com.nt.controller;

import java.net.HttpURLConnection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.CustomerEntity;
import com.nt.model.ResponseMessage;
import com.nt.service.ICustomerRegisterservice;
import com.nt.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
private	ICustomerRegisterservice customerservice;
	
	
	 @Operation(summary="create user customer",description = "e commerece online books store  regiter user")
	   @ApiResponses({
		   @ApiResponse(responseCode="201",description = "customer register successfully"),
		   @ApiResponse(responseCode="400",description="customer  register failure"),
		   @ApiResponse(responseCode="500",description="Internal server  error")
	   })
	 
	 
	@PostMapping("/customersave")
	public ResponseEntity<ResponseMessage> createCustomer(@RequestBody CustomerEntity customer)  {
		try {
			
			if(customer.getEmail() ==null || customer.getEmail().isEmpty() || customer.getName()==null || customer.getName().isEmpty()) {
				
				return 
						ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new  ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"email and name cannot be empty"));		
			}
			CustomerEntity inserCustomerRegister = customerservice.inserCustomerRegister(customer);
			 // CustomerEntitymongo customerRegister = customerservice. inserCustomerRegister(mongoId);
		
			if(inserCustomerRegister!=null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"Customer data saved successfully", inserCustomerRegister));
				
				
			}else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"Customer failed", inserCustomerRegister));
			
			}
		} catch (Exception e) {
			
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constants.FAILED,"Internal server error"));
		}
		}
	 
	 @PutMapping("/customerupdate")
	 public ResponseEntity<ResponseMessage> Updatecustomer(@RequestBody CustomerEntity customer) {
			try {
				
				if(customer.getEmail() ==null || customer.getEmail().isEmpty() || customer.getName()==null || customer.getName().isEmpty()) {
					
					return 
							ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new  ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"email and name cannot be empty"));		
				}
				if(customer.getId()==null) {
					CustomerEntity updateCustomerRegister=customerservice.UpdateCustomer(customer);
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"Customer data updated successfully", updateCustomerRegister));
			
				}
				
				else {
						CustomerEntity inserCustomerRegister = customerservice.inserCustomerRegister(customer);
						return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"customer updated successfully ", inserCustomerRegister));
					
				}}catch (Exception e) {
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constants.FAILED,"Internal server error"));
					
				}
	 }
	 @PostMapping("/cutomersaveORUpdate")
	 public ResponseEntity<ResponseMessage> SaveOrUpdatecustomer(@RequestBody CustomerEntity customer) {
		 try {
			  if(customer.getEmail() ==null || customer.getEmail().isEmpty() || customer.getName()==null || customer.getName().isEmpty()) {
				 
				 return 
						 ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new  ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"email and name cannot be empty"));		
			 }
			 if(customer.getId()==null) {
				
				 CustomerEntity createorupdateCustomerRegister=customerservice.createORupdateCustomer(customer);
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"Customer  createorupdateCustomerRegister successfully",  createorupdateCustomerRegister));
				 
			 }
			 
			 else {
				 CustomerEntity  createorupdateCustomerRegister = customerservice. createORupdateCustomer(customer);
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"custmer updated successfully", createorupdateCustomerRegister));
				 
			 }}catch (Exception e) {
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constants.FAILED,"Internal server error"));
				 
			 }
	 }
	 @GetMapping("/getByCustomerId/{id}")
	 public ResponseEntity<ResponseMessage> Updatebyidcustomer(@PathVariable Long id) {
		 CustomerEntity byCustomerId = customerservice.getByCustomerId(id);
		 if(byCustomerId!=null) {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"Customer id getting  successfully",  byCustomerId));
			
		 }else {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"custmer id getting failed", byCustomerId));
			 
		 }
		 
		 
	 }
	 
	 @GetMapping("/getallcustomers")
	public ResponseEntity<ResponseMessage> getAllCustomers() {
		List<CustomerEntity> allByCustomer = customerservice.getAllByCustomer();
	 if(allByCustomer!=null) {
		 return  ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS, "Getting all cutomers successfully",allByCustomer  ));
	 }else {
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,"Getting  all customer failed",allByCustomer));
	}
	 
		 
	
	 }
	 @GetMapping("/getallcustomerspage")
	 public ResponseEntity<ResponseMessage> getpage(@RequestParam int page,@RequestParam int size,@RequestParam String sortField,@RequestParam  String pagedirection) {
		
		 Page<CustomerEntity> getpage = customerservice.getbycustomerpagenation(page, size, sortField, pagedirection);
		 if(getpage!=null) {
			 
			 return  ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS, "Getting  cutomers  paginationsuccessfully",getpage  ));
			 
		 }else {
			 return  ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED, "Getting  cutomers  pagination Fieled",getpage  ));
			
		}
	 }
	 
	 
}

			
			
			
	 	
	 	
		
