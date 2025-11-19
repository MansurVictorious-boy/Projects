package com.nt.mongodbentity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collation = "Customersdetils")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntitymongo {
	@Id
	private String id;
	private String name;
	private String email;
}
