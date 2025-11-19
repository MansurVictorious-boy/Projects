package com.nt.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.BooksModule;
import com.nt.entity.Orders;
import com.nt.entity.UserRegister;
import com.nt.model.OrderModuleDto;
import com.nt.repository.OrderModuleRepo;
import com.nt.repository.UserRegisterRepo;
import com.nt.service.OrderService;
@Service
public class OrderServiceimpl implements OrderService {
@Autowired private OrderModuleRepo ordermoduleRepo;

@Autowired private UserRegisterRepo userregisterRepo;

@Override
public String saveOrders(OrderModuleDto orderdto) {
	// 1. check if the request body or titles are empty
	
	if(orderdto==null|| orderdto.getTitle()==null||orderdto.getTitle().isEmpty()) {
		return "No Books selected..!, Please select  at least one book to proceed...!";
	}
	
	
	// 2. Extract customer ID and selected book titles
	Long customerid =orderdto.getCustomerid();
	List<String> selectedBooks=orderdto.getTitle();
	
	
	// 3. Check whether the user is a Prime member or not
	Boolean isPrimeUser =checkPrimeUser(customerid);
	
	
	// 4. Apply rules for Non-Prime users
	if(!isPrimeUser) {
		
		
		// Non-prime users cannot order more than one book at a time
		if(selectedBooks.size()>1) {
			return "Non-prime users can select only one book..!";
		}
		List<Orders> anyLastWeekPlaced=ordermoduleRepo.findAnyLastweekPlaced(customerid);
	
		if(!anyLastWeekPlaced.isEmpty()) {
			return "Non-Prime user can place only one order per week";
		}
	
	
	}
	// 5. Iterate through each selected book and validate availability
		for( String title:selectedBooks) {
			//find  book detils by title 
			BooksModule byBookName = ordermoduleRepo.findByBookName(title);
		
		if(byBookName==null) {
			return "NO book found:" +title;
		}
		//Create a new order entity for the customer
		Orders o=new Orders();
		o.setBookId(byBookName.getId());
		o.setCustmerId(customerid);
		o.setStatus(false);// Default status = pending/unprocessed
		
		ordermoduleRepo.save(o);
		
		}
	return "Order placed successfully..Thank You....!";
}
//Helper method to check whether the user is a Prime user
private Boolean checkPrimeUser(Long customerid) {
	// Fetch user by ID
	Optional<UserRegister> userbyId = userregisterRepo.findById(customerid);
	return userbyId.map(UserRegister::getPrime).orElse(false);
	
}
}
