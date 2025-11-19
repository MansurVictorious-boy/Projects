package com.nt.model;

import java.util.List;

import lombok.Data;


@Data

public class ResponseMessage {
	private Integer statusCode;
	private String status;
	private String Message;
	private Object data;
	private List<?>list;
	public ResponseMessage(Integer statusCode, String status, String message, Object data) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.Message = message;
		this.data = data;
	}
	public ResponseMessage(Integer statusCode, String status, String message, List<?> list) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.Message = message;
		this.list = list;
	}
	public ResponseMessage(Integer statusCode, String status, String message) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.Message = message;
	}
	
	
	
	

}
