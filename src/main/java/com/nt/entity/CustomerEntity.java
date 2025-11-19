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
@Table(name="customer")//it is optional
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		
		@Column(name="name")
		private String name;
		
		@Column(name="email")
		private String email;

		@CreationTimestamp
		@Column(name="createDate",updatable = false)
	    public LocalDateTime createDate;
	
		@UpdateTimestamp
		@Column(name="updateDate")
	     public LocalDateTime updateDate;
	
		
		
		
		

	}



