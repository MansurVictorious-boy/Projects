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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ratings_entiity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="rate")
	private int rate;
	@Column(name="reviewText")
	private String reviewText;
	
	@ManyToOne
	@JoinColumn(name="book_id",updatable=false)
	private BooksModule booksModule;
	
	@ManyToOne
	@JoinColumn(name="customer_id",updatable=false)
	private CustomerEntity customerEntity;
	
	@CreationTimestamp
	@Column(name="createDate",updatable = false)
    public LocalDateTime createDate;

	@UpdateTimestamp
	@Column(name="updateDate")
     public LocalDateTime updateDate;

}
