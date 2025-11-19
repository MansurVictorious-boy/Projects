package com.nt.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Rating;
import com.nt.model.RatingDto;
import com.nt.model.ResponseMessage;
import com.nt.service.RatingService;
import com.nt.utility.Constants;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class RatingController {
	@Autowired private RatingService  ratingService ;
	
	@PostMapping("/addrating")
	public ResponseEntity<ResponseMessage> createrating(@RequestBody RatingDto ratingDto)  {
		
		
		try {
		Rating reviewrateing = ratingService.createrating(ratingDto);
		if(reviewrateing!=null) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"Added Rating successfully.....!",reviewrateing));
		}else {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,"Added Rating Failed ......!", reviewrateing));
		}
		
		} catch (Exception e) {
			//e.printStackTrace();
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,"Internal Server Error.....!"));
		}
		
	}
	
	@GetMapping("/getallratings")
	public  ResponseEntity<ResponseMessage> getallratings() {
		List<Rating> byallRatings = ratingService.getByallRatings();
		
		 if(byallRatings!=null) {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"All Ratings getting  successfully",  byallRatings));
			
		 }else {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"All Ratings  getting failed", byallRatings));
			 
	 }
	
	
	}

	


}
