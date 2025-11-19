package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.BooksModule;
import com.nt.entity.CartModule;
import com.nt.entity.CustomerEntity;

public interface CartModuleRepo extends JpaRepository<CartModule, Long> {
	CartModule findByCustomerAndBooksModule(CustomerEntity customer, BooksModule booksModule);
}
