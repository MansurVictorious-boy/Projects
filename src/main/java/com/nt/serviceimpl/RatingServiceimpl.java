package com.nt.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.BooksModule;
import com.nt.entity.CustomerEntity;
import com.nt.entity.Rating;
import com.nt.exception.BookIdNotFoundException;
import com.nt.exception.CustomerIdNotFoundExecption;
import com.nt.model.RatingDto;
import com.nt.repository.BooksModuleRepo;
import com.nt.repository.CustomerRepo;
import com.nt.repository.RatingRepo;
import com.nt.service.RatingService;
@Service
public class RatingServiceimpl implements RatingService {
	
@Autowired	 private RatingRepo ratingRepo;

    @Autowired  private  CustomerRepo customerRepo;
    
   @Autowired private BooksModuleRepo booksModuleRepo;

	@Override
	public Rating createrating(RatingDto ratingDto) {
		CustomerEntity customerEntity=customerRepo.findById(ratingDto.getCustomerid()).orElseThrow(()->new CustomerIdNotFoundExecption("Customer Id Not Found...!"));
		
		 BooksModule booksModule = booksModuleRepo.findById(ratingDto.getBookid()).orElseThrow(()->new BookIdNotFoundException("Book Id Not Found ...!"));

	Rating rr=new Rating();
	rr.setBooksModule(booksModule);
	rr.setCustomerEntity(customerEntity);
	rr.setRate(ratingDto.getRate());
	rr.setReviewText(ratingDto.getReviewText());
	ratingRepo.save(rr);
	return rr;
	}

	@Override
	public List<Rating> getByallRatings() {
		
		return ratingRepo.findAll();
	}

}
