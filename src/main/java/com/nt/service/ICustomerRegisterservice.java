package com.nt.service;




import java.util.List;

import org.springframework.data.domain.Page;

import com.nt.entity.CustomerEntity;


public interface ICustomerRegisterservice {
	public CustomerEntity inserCustomerRegister(CustomerEntity customerEntity);
  
	public CustomerEntity UpdateCustomer(CustomerEntity customerEntity);
	
	public CustomerEntity createORupdateCustomer(CustomerEntity customerEntity);
	
	public CustomerEntity getByCustomerId(Long id);
	
	public List<CustomerEntity> getAllByCustomer();
	
	
public Page<CustomerEntity>  getbycustomerpagenation(int page,int size,String sortField,String  pagedirection);
//		//for Mongodb
//	CustomerEntitymongo inserCustomerRegister(CustomerEntitymongo customerEntitymongo);
}
