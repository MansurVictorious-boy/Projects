package com.nt.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nt.entity.CustomerEntity;
import com.nt.exception.CustomerIdNotFoundExecption;
import com.nt.repository.CustomerRepo;
import com.nt.service.ICustomerRegisterservice;

@Service

public class CustomerServiceImpl implements ICustomerRegisterservice {
   @Autowired
   CustomerRepo cr;
   
   
//   @Autowired
//  CustomerRepoMongo  customerRepoMongo;
//   
//   @Autowired
//   CustomerEntitymongo  customerEntitymongo;
//   

@Override
public CustomerEntity inserCustomerRegister(CustomerEntity customerEntity) {
	CustomerEntity cus = cr.save(customerEntity);

	return cus;
	
	
	
}
//For Mongodb

//  public CustomerEntitymongo inserCustomerRegister(CustomerEntitymongo
//  customerEntitymongo) { CustomerEntitymongo
//  cem=customerRepoMongo.save(customerEntitymongo); return cem; }
// 

@Override
public CustomerEntity UpdateCustomer(CustomerEntity customerEntity) {
	CustomerEntity ce=cr.save(customerEntity);
	
	return ce;
}
//For Mongodb

//public CustomerEntitymongo UpdateCustomer(CustomerEntitymongo customerEntitymongo) {
//	CustomerEntitymongo ce=customerRepoMongo.save(customerEntitymongo);
//	return ce;
//}

@Override
public CustomerEntity createORupdateCustomer(CustomerEntity customerEntity) {
	if(customerEntity.getId() ==null) {
		cr.save(customerEntity);
	}else {
		Optional<CustomerEntity> byId = cr.findById(customerEntity.getId());
		if (byId.isPresent()) {
			CustomerEntity existData = byId.get();
			existData.setName(customerEntity.getName());
			existData.setEmail(customerEntity.getEmail());
			cr.save(existData);
	}else {
	throw new RuntimeException("Custmer Not Found");
	}
}
	
return customerEntity;
}
//For Mongodb

//public CustomerEntitymongo createORupdateCustomer(CustomerEntitymongo customerEntitymongo) {
//	if(customerEntitymongo.getId() ==null) {
//		customerRepoMongo.save(customerEntitymongo);
//	}else {
//		Optional<CustomerEntitymongo> byId = customerRepoMongo.findById(customerEntitymongo.getId());
//		if (byId.isPresent()) {
//			CustomerEntitymongo existData = byId.get();
//			existData.setName(customerEntitymongo.getName());
//			existData.setEmail(customerEntitymongo.getEmail());
//			customerRepoMongo.save(existData);
//		}else {
//			throw new RuntimeException("Custmer Not Found");
//		}
//	}
//	return customerEntitymongo;
//}


@Override
public CustomerEntity getByCustomerId(Long id) {
	Optional<CustomerEntity> byId = cr.findById(id);
	if(!byId.isPresent()) {
	//throw new RuntimeException("Customer Id not found");
	throw new CustomerIdNotFoundExecption("Custmer Not Found");
}
	
	return byId.get();
}
//For Mongodb
//public CustomerEntitymongo getByCustomerId(String id) {
//	Optional<CustomerEntitymongo> byId = customerRepoMongo.findById(id);
//	if(!byId.isPresent()) {
//		//throw new RuntimeException("Customer Id not found");
//		throw new CustomerIdNotFoundExecption("Custmer Not Found");
//	}
//	return byId.get();
//}

@Override
public List<CustomerEntity> getAllByCustomer() {
	List<CustomerEntity> list = cr.findAll();
	
	return list;
}
//For Mongodb


//public List<CustomerEntitymongo> getAllByCustomers() {
//	List<CustomerEntitymongo> list = customerRepoMongo.findAll();
//	return list;
//}

@Override
public Page<CustomerEntity> getbycustomerpagenation(int page, int size,
		String sortField, String pagedirection) {
 Sort sort=	pagedirection.equalsIgnoreCase("asc")? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
	
 PageRequest pagerequest = PageRequest.of(page, size,sort);

 return cr.findAll(pagerequest);
}
//For Mongodb

//public Page<CustomerEntitymongo> getbycustomerpagenations(int page, int size,
//		String sortField, String pagedirection) {
//	Sort sort=	pagedirection.equalsIgnoreCase("asc")? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
//	
//	PageRequest pagerequest = PageRequest.of(page, size,sort);
//	
//	return customerRepoMongo.findAll(pagerequest);
//
//}




}
	