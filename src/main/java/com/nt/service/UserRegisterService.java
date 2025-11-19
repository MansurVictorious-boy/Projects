package com.nt.service;




import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nt.entity.UserRegister;
import com.nt.model.UserRequest;
import com.nt.model.UserRequestDto;

public interface UserRegisterService {
	public UserRegister insertUserRegister(UserRequestDto userRequestDto);

	public UserRequest getUserRegisterDetails(Long id);

	public UserRegister checkUserDetails(UserRequestDto userRequestDto);
	
	public UserRegister uploadMultiUserRegister(UserRequestDto userDto, MultipartFile[] files);

	public List<UserRegister> getAllUsers();

	UserRegister userLoginCreate(UserRequestDto userRequestDto);
	
}
