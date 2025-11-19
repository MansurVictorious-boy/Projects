package com.nt.mongodbentity;


import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="userregister")
public class UserRegisterMongodb {
	@Id
	
	private String id;
	
	private String firstname;
	
	private String lastname;

	private String email;

	private long contactid;

	private String Password;
	

}
