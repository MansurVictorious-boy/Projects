package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="file_entity")
public class FileEntity {
  
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="fileName")
	private String fileName;
	
	@Column(name="fileType")
	private String fileType;
	
	@Column(columnDefinition = "longblob")
	@Lob
	private byte[] data;
	
	
	@CreationTimestamp
	@Column(name="createDate")
	public LocalDateTime createDate;
	
	
	@UpdateTimestamp
	@Column(name="updateDate")
	public LocalDateTime updateDate;
	
}
