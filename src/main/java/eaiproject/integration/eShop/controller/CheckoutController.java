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
import eaiproject.integration.eShop.business.message.OrderMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class CheckoutController {

    @Autowired private CheckoutAdapter checkoutAdapter;

    @GetMapping(path = "/checkout", produces = "text/plain")
    public ResponseEntity<String> getCheckout(){
        List<OrderMessage.OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderMessage.OrderItem("1", UUID.randomUUID().toString(), 15));
        OrderMessage orderMessage = new OrderMessage(
        		UUID.randomUUID().toString(), // OrderId (New)
        		"1", // CustomerId
        		2000.00, // Amount - Double
        		3, //NumersOfItem - Integer
        		orderItems, //Items - List
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
