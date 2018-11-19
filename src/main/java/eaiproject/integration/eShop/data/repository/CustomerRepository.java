package eaiproject.integration.eShop.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import eaiproject.eaiprojectOrderPlacement.data.domain.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	public Customer findCustomerByCustomerId(@Param("customer_id") Integer CustomerId);

}