package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nt.entity.BooksModule;
import com.nt.entity.Orders;
@Repository
public interface OrderModuleRepo extends JpaRepository<Orders, Long> {

//	@Query(value="SELECT * FROM orders o WHERE o.customerid = :customerid AND o.created_date > CURDATE 7 DAY",nativeQuery = true)
//	public List<Orders> findAnyLastWeekPlaced( Long customerid);
//
//	
//	
//	@Query(value="SELECT b FORM  BooksModule b WHERE b.title=:title")
//	public BooksModule findByBookName(String title);
	//Custom query to find all orders placed by a specific customer within the last 7 days.
    // This is useful for restricting non-prime users from placing  more than one order per week.
	  @Query(value = "SELECT * FROM orders o WHERE o.customer_id = :customerId AND o.created_date > CURDATE() - INTERVAL 7 DAY", nativeQuery = true)
	  public List<Orders> findAnyLastweekPlaced(Long customerId);

	  
	    //whether a book with the given title actually exists or not.
	  @Query(value = "SELECT b FROM BooksModule b WHERE b.title = :title")
	  public BooksModule findByBookName(String title);
	
}
