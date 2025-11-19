package com.nt.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.CartModule;
import com.nt.model.ResponseMessage;
import com.nt.service.CartModuleSevice;
import com.nt.utility.Constants;

@RestController
@RequestMapping("/api")
public class CartModuleController {
	
	@Autowired private CartModuleSevice cartModuleService;

	
	@PostMapping("/addcart")
	public ResponseEntity<ResponseMessage> createCustmers(@RequestParam Long custemerId,
			                                              @RequestParam Long bookId,
			                                              @RequestParam int quantity) {
		try {
		CartModule toCart = cartModuleService.addToCart(custemerId,bookId,quantity);
		if(toCart!=null) {
	       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "added cart successfully", toCart));
		} else {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "added cart Failed", toCart));
	       }
		 }catch (Exception e) {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}
	
	@DeleteMapping("/addcart/{id}")
	public ResponseEntity<ResponseMessage> deleteCart(@PathVariable Long id) {
		 cartModuleService.deleteToCart(id);
	   return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "delete  cart successfully"));
		
	}
	
	
	
	
	
	
	
}
