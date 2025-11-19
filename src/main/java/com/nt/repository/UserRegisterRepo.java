package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.UserRegister;
@Repository  //it is a optional

public interface UserRegisterRepo extends JpaRepository<UserRegister,Long> {
	
	public UserRegister findByEmail (String email); 
	

}
