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
import eaiproject.integration.eShop.stream.message.EventMessage;
import eaiproject.integration.eShop.stream.message.OrderMessage;
import eaiproject.integration.eShop.stream.sender.MessageEventSender;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class CheckoutController {

    @Autowired private CheckoutAdapter checkoutAdapter;
    @Autowired private MessageEventSender eventSender;

    /**
     * Generate a Checkout from the eShop with some Shampoos and the customer credientials
     * @param do a http-request with /checkout to start the process
     * @return orderMessage to start the Use Case
     * @author Lukas Weber
     */
    @GetMapping(path = "/checkout", produces = "text/plain")
    public ResponseEntity<String> getCheckout(){
    	List<Shampoo> shampoos = new ArrayList<>();
    	
    	// Generate some Shampoos
    	shampoos.add(new Shampoo(new Integer(42), "Deep Space", "Axe", "AllinOneXL", 8.30));
    	shampoos.add(new Shampoo(new Integer(42), "Africa", "Axe", "AllinOneXL", 9.30));
    	shampoos.add(new Shampoo(new Integer(42), "Adrenaline", "Axe", "AllinOneXL", 5.30));
    	shampoos.add(new Shampoo(new Integer(42), "Excite", "Axe", "AllinOneXL", 10.30));
    	
        OrderMessage orderMessage = new OrderMessage(
        		UUID.randomUUID().toString(), // OrderId (New)
        		"1", // CustomerId
        		0.00, // Amount - Double // Wenn nichts mitgegeben wird das berechnet von den Shampoos
        		3, //NumersOfItem - Integer
        		shampoos, // order
        		"checkout", //Status
        		"Lukas", // First Name
        		"Gehrig", // Last Name
        		"Lukas Gehrig", // shipping_address_name
        		"Musterstrasse 1", // shipping_address_street
        		"8000 Zürich", // shipping_address_location
        		"", // loyalityPoints
        		"DHL" // parcel_service
        		); 
        eventSender.send(new EventMessage<>("RequestPayment", orderMessage));
        return ResponseEntity.ok(orderMessage.toString());
    }
}
