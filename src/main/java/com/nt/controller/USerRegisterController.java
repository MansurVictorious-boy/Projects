package com.nt.controller;


import java.net.HttpURLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.entity.UserRegister;
import com.nt.model.ResponseMessage;
import com.nt.model.UserRequest;
import com.nt.model.UserRequestDto;
import com.nt.service.UserRegisterService;
import com.nt.utility.Constants;
import com.nt.utility.JwtUtilService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "UserRegisterController ",description = "UserRegister Regsiter and Login")

@RestController
@RequestMapping("/api")
public class USerRegisterController {
@Autowired JwtUtilService jwtUtilService;


	private static final Logger logger = LoggerFactory.getLogger(USerRegisterController.class);
	
	@Autowired
	private UserRegisterService userRegisterService;
	
	
	
	 @Operation(summary = "Create User Register",description = "e commerece online books store  register the users")

	
	 
	 
	 @ApiResponses({
	     @ApiResponse(responseCode = "201",description = "user register successfully"),
	     @ApiResponse(responseCode = "400",description = "user register failure"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     })

	 
	 
	@PostMapping("/userregisters")
//	public String createUserRegister(@RequestBody UserRegister userRegister) {
		//public ResponseEntity<ResponseMessage> createUserRegister(@RequestBody
			 //	UserRequestDto userRequestdto) {
	
		//userRegisterService.insertUserRegister(userRegister);
		//String insertUserRegister=userRegisterService.insertUserRegister(userregister);
//		return "User Register saved Successfully";
	
		 //return ResponseEntity.ok(new ResponseMessage(HttpsURLConnection.HTTP_CREATED, Constants.SUCCESS,"online bookstore save Successfully",insertUserRegister));
		
	 public ResponseEntity<ResponseMessage> createUserRegister(@RequestBody UserRequestDto userRequestDto) {
				   
		logger.info("Regpstration controller layyer calling or started");	
		try {
				if(userRequestDto.getEmail()==null || userRequestDto.getEmail().isEmpty() || userRequestDto.getPassword() ==null || userRequestDto.getPassword().isEmpty()) {
					
					logger.debug("Recevied userRegData: {}",userRequestDto);
					
					logger.warn("missing email and passsword registation request");
					
					logger.error("User Registration email or password missing :Bad reg data");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and passowrd cannot be empty"));
				}
				 UserRegister userRegister = userRegisterService.insertUserRegister(userRequestDto);
				 if(userRegister!=null) {
					 
					 logger.info("Messsage return eco-system=\"BOOKSTORE_ONLINE_REGISTRATION_CREATION_SUCCESS\" .");
//				       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "online bookstore save successfully", userRegister));
						return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "online bookstore save successfully" ,userRegister));
				 }else {
					 logger.info("Messaage return eco-system=\"BOOKSTORE_ONLINE_REGISTRATION_CREATION_FAILED\" .");
					logger.warn("USer Registration Service Retutn null :registration failed");
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "User Register Failed" ,userRegister));
		 
				 }}catch (Exception e) {
					 
					 logger.error("New User Creation Process failed in Bookstore-DB .Exception:"+e.getMessage());
					 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
				}
			}
	 
	 
	 @Operation(summary="Create User Register",description="e commerece online books store register the user")
	 @ApiResponses({
		 @ApiResponse(responseCode="200",description="user Login successfully"),
		 @ApiResponse(responseCode="400",description="user Login failure"),
		 @ApiResponse(responseCode="500",description="Internal server error")
	 
	 })
	 @PostMapping("/userlogin")
	 public ResponseEntity<ResponseMessage> checkLogin(@RequestBody UserRequestDto userRequestDto){
		try {
			if(userRequestDto.getEmail()==null || userRequestDto.getEmail().isEmpty()||userRequestDto.getPassword()==null|| userRequestDto.getPassword().isEmpty()) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED, "Email and password can not be empty"));
			}
			UserRegister checkUserDetails=userRegisterService. checkUserDetails(userRequestDto);
		   if(checkUserDetails!=null) {
			   String token = jwtUtilService.generateToken(userRequestDto.getEmail());
			   return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS, "Login successfully", token));
		   }else {
			   return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED, "Invaild Creditials.....!"));
		   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constants.FAILED,"Internal Server Error"));
	
	 }
	 
	 
	 @GetMapping("/userDetails/{id}")
	 public UserRequest	 getMethodName(@PathVariable Long id) {
		 UserRequest registerDetails = userRegisterService.getUserRegisterDetails(id);
		 return registerDetails;
	 	
	 }

	 
	 @PostMapping("/userregisteruploadmultifiles")
	
	 public ResponseEntity<ResponseMessage> UserRegisterandUplaodfiles(@RequestParam String jsondata,@RequestParam MultipartFile[] files){
		 try {
			UserRequestDto userDto=new ObjectMapper().readValue(jsondata,UserRequestDto.class);
			UserRegister userRegister=userRegisterService.uploadMultiUserRegister(userDto, files);
			if (userRegister !=null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"user and files are uplaoded sucessfully",userRegister));
				
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"user and files are uplaoded failed"));
			
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constants.FAILURE,"Internal Server error"));

		}
	 }
	 
	 @GetMapping("/getallusers")
	 public List<UserRegister> getallusers() {
		  List<UserRegister> fetchallusers = userRegisterService.getAllUsers();
		 return fetchallusers;
		 
		 
	 }
	 
}

