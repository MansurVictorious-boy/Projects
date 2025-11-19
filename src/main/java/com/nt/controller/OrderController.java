package com.nt.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.OrderModuleDto;
import com.nt.model.ResponseMessage;
import com.nt.service.OrderService;
import com.nt.utility.Constants;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class OrderController {
  
	
	@Autowired private OrderService service;
	
	
	
	@PostMapping("/orderplaced")
	public ResponseEntity<ResponseMessage> orderCreated(@RequestBody OrderModuleDto  orderdto) {
		try {
			String saveOrders = service.saveOrders(orderdto);
			if(saveOrders.toLowerCase().contains("success")) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Order Placed sucessfully....!",saveOrders));
				
			}else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Order Placed Failed....!",saveOrders));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal sever error....!"));
			
		}
		
		
	}
	
}
