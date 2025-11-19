package com.nt.model;

import lombok.Data;
@Data
public class ErrorresponseMessage {

	
	public Integer statusCode;
	
	public String status;
	
	public String message;
	
	//public List<?> list;
	

	private Object data;

	public ErrorresponseMessage(Integer statusCode, String status, String message, Object data) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.data = data;
	}

	
	
	
	
	
}
