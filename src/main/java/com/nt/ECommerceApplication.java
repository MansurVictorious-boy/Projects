package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
        title = "Ecommerc Book Store Management",
        version = "1.0",
        description = "Welcome to the Naresh I Techlogies",
        contact = @Contact(name ="Naresh IT Technology",email = "basha@gmail.com")))

@SpringBootApplication
@EnableCaching
public class ECommerceApplication {

	public static void main(String[] args) {
		 SpringApplication.run(ECommerceApplication.class,args);
		
	  
	}

}
