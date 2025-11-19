package com.nt.exception;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nt.model.ErrorresponseMessage;
import com.nt.utility.Constants;

@ControllerAdvice
public class Rest_GobalException {
	@ExceptionHandler(CustomerIdNotFoundExecption.class)
	public ResponseEntity<Object> handleExecptions(CustomerIdNotFoundExecption ce){
		
//		List<String> deatils=new  ArrayList<>();
//		
//		deatils.add("ErrorDetils:CustomerIdNotFoundExecption");
//		deatils.add("ErrorMesage:"+ce.getLocalizedMessage());
//		deatils.add("TimeStamp:"+System.currentTimeMillis());
//
//
//		ErrorresponseMessage error=new ErrorresponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILURE, "Not_Found", deatils);
//		return ResponseEntity.ok(error);
     Map<String,Object> hm= new HashMap<>();
     hm.put("ErrorDetils", "CustomerIdNotFoundExcption");
     hm.put("ErrorMessage", ce.getLocalizedMessage());
     hm.put("TimeStamp", System.currentTimeMillis());
     ErrorresponseMessage error=new ErrorresponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILURE,"Not_FOund",hm);
	
     //return ResponseEntity.ok(error);
     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    
	}
	@ExceptionHandler(BookIdNotFoundException.class)
	public ResponseEntity<Object>custhandleExc(BookIdNotFoundException exc){
		Map<String,Object> hm1=new HashMap<>();
		hm1.put("ErrorDetils", "CustomerIdNotFoundExcption");
	     hm1.put("ErrorMessage", exc.getLocalizedMessage());
	     hm1.put("TimeStamp", System.currentTimeMillis());
	     ErrorresponseMessage error=new ErrorresponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILURE,"Not_Found",hm1);
		
	     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	

}
