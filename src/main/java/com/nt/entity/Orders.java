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
@Table(name = "orders") // it is optional
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "bookId")
	private Long bookId;
	
	@Column(name = "custmerId")
	private Long custmerId;
	
	@Column(name = "status")
	private Boolean status;
	
	@CreationTimestamp
	@Column(name = "createdDate" ,updatable = false)
	public LocalDateTime createdDate;
	
	
	@UpdateTimestamp
	@Column(name = "updatedDate")
	public LocalDateTime updatedDate;
	

}