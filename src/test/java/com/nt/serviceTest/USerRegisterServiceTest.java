package com.nt.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nt.entity.UserRegister;
import com.nt.model.UserRequestDto;
import com.nt.repository.UserRegisterRepo;
import com.nt.serviceimpl.UserRegisterServiceimpl;

@SpringBootTest
public class USerRegisterServiceTest {

	
	@MockBean
	private UserRegisterRepo userrr;//Fake DB
	
	
	@Autowired
	private UserRegisterServiceimpl ursimpl;//original service
	
	
	
	@Test
	public void TestInsertUserRegister() {
		//step1:Create Input Data(like from postman or frontend)
		UserRequestDto input=new UserRequestDto();
		input.setFirstName("Latheeef");
		input.setLastName("Syed Abdul");
		input.setEmail("latheef1422gmail.com");
		input.setPassword("Pass@ine234");
		//step2:Create Fake Db Output (as if user saved in DB)
		UserRegister savedUser = new UserRegister();
		savedUser.setId(1L);
		savedUser.setFirstname("Srinu");
		savedUser.setLastname("Lateesha");
		savedUser.setEmail("gopi@gmail.com");
		
		savedUser.setPassword(Base64.getEncoder().encodeToString("Pass@123".getBytes()));
		
		
		
		
		
		//step3:mock test call
		when(userrr.save(any(UserRegister.class))).thenReturn(savedUser);
		
		//step4:Call actual Service method
		UserRegister registerresult = ursimpl.insertUserRegister(input);
		
		
		//Step5:Check(Verify)input
		assertNotNull(registerresult);
		assertEquals("Latheeef", registerresult.getFirstname());
		assertEquals("latheef1422gmail.com", registerresult.getEmail());
		
		
		//save() called only once
		
		verify(userrr,times(1)).save(any(UserRegister.class));
		
	}
	
}
