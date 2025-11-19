package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="cart_module")
public class CartModule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int quantity;
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "book_id" ,updatable = false)
	private BooksModule booksModule;
	
	@ManyToOne
	@JoinColumn(name = "custmer_id" ,updatable = false)
	private CustomerEntity customer;
	
	
	@CreationTimestamp
	@Column(name = "createdDate" ,updatable = false)
	public LocalDateTime createdDate;
	
	
	@UpdateTimestamp
	@Column(name = "updatedDate")
	public LocalDateTime updatedDate;
	
	public CartModule(int quantity, BooksModule booksModule, CustomerEntity customer) {
		super();
		this.quantity = quantity;
		this.booksModule = booksModule;
		this.customer = customer;
	}
	
	
}
