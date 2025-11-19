package com.nt.serviceimpl;



import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nt.entity.FileEntity;
import com.nt.entity.UserRegister;
import com.nt.model.UserRequest;
import com.nt.model.UserRequestDto;
import com.nt.mongodbentity.UserRegisterMongodb;
import com.nt.repository.FileRepo;
import com.nt.repository.UserRegisterRepo;
import com.nt.repository.mongodb.UserRegisterMongodbRepo;
import com.nt.service.UserRegisterService;
@Service
public class UserRegisterServiceimpl implements UserRegisterService,UserDetailsService {
	
	private static final Logger logger=LoggerFactory.getLogger(UserRegisterServiceimpl.class);
	@Autowired
	private UserRegisterRepo userRegisterRepo;
	
	
	//For MongoDb Repo
	@Autowired
	UserRegisterMongodbRepo userRegisterMongodbRepo;
	
	
  @Autowired private FileRepo filerepo;
  
  
	@Override
	public UserRegister insertUserRegister(UserRequestDto userRequestDto) {
		
		logger.info("Registation service layer calling or started");
		UserRegister u=new UserRegister();
		UserRegisterMongodb u1=new UserRegisterMongodb();
		try {
			
		
			u.setFirstname(userRequestDto.getFirstName());
			u.setLastname(userRequestDto.getLastName());
			u.setEmail(userRequestDto.getEmail());
			
			
			u.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));

			u.setContactid(userRequestDto.getContactId());
			logger.info("Regisstation service layer calling or ended");
		   userRegisterRepo.save(u);
		  
		   
		   
		   //for MongoDB Connection
		   
		   
		   u1.setFirstname(userRequestDto.getFirstName());
		   u1.setLastname(userRequestDto.getLastName());
		   u1.setEmail(userRequestDto.getEmail());
		   
		   
		   u1.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
		   
		   u1.setContactid(userRequestDto.getContactId());
		   logger.info("Regisstation service layer calling or ended");
		   userRegisterMongodbRepo.save(u1);
		} catch (Exception e) {
			logger.error("New user creation process failed in Bookstore-DB .Exception:"+e.getMessage());
			e.printStackTrace();
		}
		return u;
	}//here u means user
	
	
	@Override
	public UserRequest getUserRegisterDetails(Long id) {
		Optional<UserRegister> byId = userRegisterRepo.findById(id);
		UserRegister userRegister=byId.get();
		
		return new UserRequest(userRegister.getFirstname(),userRegister.getLastname());
	}
	
	
	
	@Override
	public UserRegister checkUserDetails(UserRequestDto userRequestDto) {
		
UserRegister findbyEmailid = userRegisterRepo.findByEmail(userRequestDto.getEmail());
		
		if(findbyEmailid!=null) {
			
			String decode = new String(Base64.getDecoder().decode(findbyEmailid.getPassword()));
			
			if(decode.equals(userRequestDto.getPassword())) {
				
				return findbyEmailid;
			}
			
		}
		return findbyEmailid;
		//return Optional.ofNullable(userRegisterRepo.FindByEmailid(userRequestDto.getEmailid()))
	   //    .filter(user -> new String(Base64.getDecoder().decode(user.getPassword()))
	  //     .equals(userRequestDto.getPassword()))
      //  .orElse(null); 




}
	@Override
	public UserRegister userLoginCreate(UserRequestDto userRequestDto) {

	    // Step 1: Email check
	    UserRegister byEmail = userRegisterRepo.findByEmail(userRequestDto.getEmail());

	    // Step 2: If user exists, then verify password
	    if (byEmail != null) {

	        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

	        //  Correctly check raw password vs encoded password from DB
	        boolean isPasswordMatch = pwdEncoder.matches(
	                userRequestDto.getPassword(),
	                byEmail.getPassword()
	        );

	        if (isPasswordMatch) {
	            //  Password correct → return user
	            return byEmail;
	        } else {
	            //  Password incorrect
	            return null;
	        }
	    }

	    //  User not found
	    return null;
	}



	@Override
	public UserRegister uploadMultiUserRegister(UserRequestDto userDto, MultipartFile[] files) {
		UserRegister ur=new UserRegister();
		try {

		ur.setFirstname(userDto.getFirstName());
		ur.setLastname(userDto.getLastName());
		ur.setEmail(userDto.getEmail());
		
		
		ur.setPassword(Base64.getEncoder().encodeToString(userDto.getPassword().getBytes()));

		ur.setContactid(userDto.getContactId());
	   userRegisterRepo.save(ur);
	   if(files!=null && files.length>0) {
		   for (MultipartFile multipartFile : files) {
			   FileEntity fe=new FileEntity();
			 
				fe.setFileName(multipartFile.getOriginalFilename());
				fe.setFileType(multipartFile.getContentType());
				fe.setData(multipartFile.getBytes());
				filerepo.save(fe);
		   }
			
		}
	   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ur;
	}


	@Override
	@Cacheable("getallusers")
	public List<UserRegister> getAllUsers() {
		List<UserRegister> allusers = userRegisterRepo.findAll();
		System.err.println("All user are getting in the db table");
		return allusers;
	}


	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserRegister user = userRegisterRepo.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
	}
	
	
	
}
