package eaiproject.integration.eShop.business.service;

import eaiproject.integration.eShop.data.domain.Customer;
import eaiproject.integration.eShop.data.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class eShopService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private Logger logger = LoggerFactory.getLogger(eShopService.class);
	
    public Customer caluclateLoyalityPoints(Integer customerId, Integer loyalityPoints) throws Exception {
        logger.info("caluculateLoyalityPoints() for a customer with Id:  " + customerId + " and points " + loyalityPoints);
        // ...
        Customer customer = customerRepository.findById(customerId).orElse(null);
        
        // Calculate
        Integer newLoyalityPoints = 100 / loyalityPoints;
        logger.info("LoyalityPoints are calculated: " + newLoyalityPoints);
        
        return customer;
        
    }
    
    public Customer addLoyalityPoints(Integer customerId, Integer loyalityPoints) throws Exception {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        
        // Calculate
        Integer newLoyalityPoints = 100 / loyalityPoints;
        
        logger.info("LoyalityPoints are calculated: " + newLoyalityPoints);
    	// Add
        customer.setNmbr_of_loyalty_points(customer.getNmbr_of_loyalty_points() + newLoyalityPoints);
        logger.info("New LoyalityPoints are: " + customer.getNmbr_of_loyalty_points());
        return customer;
    }

}
