/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package eaiproject.integration.eShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import eaiproject.integration.eShop.business.client.CheckoutAdapter;
import eaiproject.integration.eShop.data.domain.Shampoo;
import eaiproject.integration.eShop.stream.message.OrderMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class CheckoutController {

    @Autowired private CheckoutAdapter checkoutAdapter;

    @GetMapping(path = "/checkout", produces = "text/plain")
    public ResponseEntity<String> getCheckout(){
    	List<Shampoo> shampoos = new ArrayList<>();
    	
    	// Generate some Shampoos
    	shampoos.add(new Shampoo(Integer.parseInt(UUID.randomUUID().toString()), "Africa", "Axe", "AllinOneXL", 8.30));
    	shampoos.add(new Shampoo(Integer.parseInt(UUID.randomUUID().toString()), "Africa", "Black", "AllinOneXL", 9.30));
    	shampoos.add(new Shampoo(Integer.parseInt(UUID.randomUUID().toString()), "Africa", "Choco", "AllinOneXL", 5.30));
    	shampoos.add(new Shampoo(Integer.parseInt(UUID.randomUUID().toString()), "Africa", "White", "AllinOneXL", 10.30));
    	
        OrderMessage orderMessage = new OrderMessage(
        		UUID.randomUUID().toString(), // OrderId (New)
        		"1", // CustomerId
        		0.00, // Amount - Double // Wenn nichts mitgegeben wird das berechnet von den Shampoos
        		3, //NumersOfItem - Integer
        		shampoos, // order
        		"checkout", //Status
        		"", // First Name
        		"", // Last Name
        		"", // shipping_address_name
        		"", // shipping_address_street
        		"", // shipping_address_location
        		"", // loyalityPoints
        		"" // parcel_service
        		); 
        checkoutAdapter.doCheckout(orderMessage);
        return ResponseEntity.ok(orderMessage.toString());
    }
}
