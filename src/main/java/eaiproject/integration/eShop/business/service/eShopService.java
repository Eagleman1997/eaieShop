package eaiproject.integration.eShop.business.service;

import eaiproject.integration.eShop.data.domain.Customer;
import eaiproject.integration.eShop.data.repository.CustomerRepository;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class eShopService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private Logger logger = LoggerFactory.getLogger(eShopService.class);
	
	/**
	 * For Calculating the new loyality Points, but not setting only calculating
	 * @param customerId
	 * @param amount
	 * @return customer
	 * @throws Exception
	 * @author Lukas Weber
	 */
    public Customer caluclateLoyalityPoints(Integer customerId, Double amount) throws Exception {
        
    	logger.info("caluculateLoyalityPoints() for a customer with Id:  " + customerId + " and last amount " + amount);
        
    	//find the customer
        Customer customer = customerRepository.findById(customerId).orElse(null);
    	
        // Calculate
        Double calcAmout = 100 / amount;
        Integer newLoyalityPoints = (int) Math.round(calcAmout);
        
        logger.info("LoyalityPoints from the last amount are calculated: " + newLoyalityPoints);
        
        // make a new Customer temporary for the calculated Points
        Customer calcCustomer = new Customer(
        		customer.getCustomerId(),
        		newLoyalityPoints
        		);
        
        return calcCustomer;
    }
    
    
    /**
     * For setting the loyality Points 
     * @param calcCustomer (has the calculated Points in it)
     * @return
     * @throws Exception
     * @author Lukas Weber
     */
    public Customer addLoyalityPoints(Customer calcCustomer) throws Exception {
        
    	//find the customer
        Customer customer = customerRepository.findById(calcCustomer.getCustomerId()).orElse(null);
    	
    	// Add
        customer.setNmbr_of_loyalty_points(customer.getNmbr_of_loyalty_points() + calcCustomer.getNmbr_of_loyalty_points());
        logger.info("New LoyalityPoints are: " + customer.getNmbr_of_loyalty_points());
        
        return customer;
    }

}
