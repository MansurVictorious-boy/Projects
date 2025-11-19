package com.nt.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.BooksModule;
import com.nt.model.ResponseMessage;
import com.nt.service.BooksService;
import com.nt.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@RestController
public class BooksController {
	@Autowired BooksService booksService;

	 @Operation(summary = "Create User Custmers",description = "e commerece online books store  register the users")
	    @ApiResponses({
	     @ApiResponse(responseCode = "201",description = "user books saved successfully"),
	     @ApiResponse(responseCode = "400",description = "user books saved failure"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     })
	@PostMapping("/savebooks")
	public ResponseEntity<ResponseMessage> createBooks(@RequestBody BooksModule booksModule) {
		 
		try {
		if(booksModule.getName()==null || booksModule.getName().isEmpty() || booksModule.getTitle() ==null || booksModule.getTitle().isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "books name and title cannot be empty"));
		}
	  
		BooksModule custmerSaveBooks = booksService.custmerSaveBooks(booksModule);
		
		if(custmerSaveBooks!=null) {
	       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "custmer  books save successfully", custmerSaveBooks));
		} else {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "custmer  books save Failed", custmerSaveBooks));

	       }

		 }catch (Exception e) {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}
	 
	    @GetMapping("/getAllBooks")
		public ResponseEntity<ResponseMessage> getAllBook() {
			try {
			List<BooksModule> getAllCusBooks = booksService.custmergetAllBooks();
			if(getAllCusBooks!=null) {
		       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "custmer All books getting successfully", getAllCusBooks));
			} else {
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "custmer All books getting failed", getAllCusBooks));
		       }

			 }catch (Exception e) {
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
			}
		}
	    
	    @GetMapping("/getCustBook/{id}")
		public ResponseEntity<ResponseMessage> getByBook(@PathVariable Long id) {
			try {
			 BooksModule byCustmerBookid = booksService.getByCustmerBookid(id);
			if(byCustmerBookid!=null) {
		       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "custmer Book Id getting successfully", byCustmerBookid));
			} else {
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "custmer Book Id getting  failed", byCustmerBookid));
		       }

			 }catch (Exception e) {
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
			}
		}
		 
	
	
}