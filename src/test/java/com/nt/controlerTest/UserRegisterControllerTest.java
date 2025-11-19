package com.nt.controlerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.controller.USerRegisterController;
import com.nt.entity.UserRegister;
import com.nt.model.UserRequestDto;
import com.nt.service.UserRegisterService;

@WebMvcTest(USerRegisterController.class)
public class UserRegisterControllerTest {
	
	
	@Autowired
	  private MockMvc mockMvc;
	
	@MockBean
	private UserRegisterService  userRegisterService;
	
	
	
	@Test
	public void TestUSerRegisteration()throws  JsonProcessingException, Exception {
		
		//Input date 
		UserRequestDto userrequest=new UserRequestDto();
		userrequest.setFirstName("Mansur Basha");
		userrequest.setLastName("Syed");
		userrequest.setEmail("basha050@gmail.com");
		userrequest.setPassword("pass@143");
		
		
		//Mock service response
		UserRegister mockresponse=new UserRegister();
		mockresponse.setId(1L);
		mockresponse.setEmail("syed@gmail.com");
		
		
		//Mock service call
		when(userRegisterService.insertUserRegister(userrequest)).thenReturn(mockresponse);
		
		
		//Perform POST and Validate response
		mockMvc.perform(post("/userregisters")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userrequest)))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
			
	}
	@Test
	public void getAllUSers() throws JsonProcessingException, Exception {
		//input request
		UserRegister list=new UserRegister();
		list.setFirstname("Shamsheer");
		list.setLastname("Basha");
		list.setEmail("Shamsheer143@gmil.com");
		
		
		
		//input request
		UserRegister list1=new UserRegister();
		list1.setFirstname("Mansur");
		list1.setLastname("Basha");
		list1.setEmail("Mansur143@gmil.com");
		
		
		List<UserRegister>asList=Arrays.asList(list,list1);
		
		
		//mock call
		when(userRegisterService.getAllUsers()).thenReturn(asList);
			
		
		//mock call Get
		mockMvc.perform(get("/getallusers")
			   .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
	           .andExpect(content()
	           .contentType(MediaType.APPLICATION_JSON))
		       .andExpect(content().json(new ObjectMapper().writeValueAsString(asList)));

	}
	@Test
	public void testUserLogin() throws Exception{
		//Input data
		UserRequestDto ur=new UserRequestDto();
		ur.setEmail("Rafi143@gmail.com");
		ur.setFirstName("Rafi");
		ur.setLastName("Shai Mohammed");
		ur.setPassword("rafi143@gmail.com");
		
		//Mock service response
		UserRegister mr=new UserRegister();
		mr.setId(1L);
		mr.setEmail("ramanaaaaaaaaaaaaaaaaaaaa@gmail.com");
		
		
		//Mock service call
		when(userRegisterService.checkUserDetails(ur)).thenReturn(mr);
		
		
		//Perform Post and Validate
		mockMvc.perform(post("/userlogin")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(ur)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}


	
	

}
