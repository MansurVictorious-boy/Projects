package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="register")//it is optional
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegister {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="firstname")
	private String firstname;
	@Column(name="lastname")
	private String lastname;
	@Column(name="email")
	private String email;
	@Column(name="contactid")
	private long contactid;
	@Column(name="Password")
	private String Password;
	
	@Column(name = "prime",columnDefinition = "TINYINT(1)")//Here TINYINT is used for prime member or not(0,1)
	private Boolean prime;
	
	@CreationTimestamp
	@Column(name = "createdDate")
	public LocalDateTime createdDate;
	
	
	@UpdateTimestamp
	@Column(name = "updatedDate")
	public LocalDateTime updatedDate;
	
	

}
