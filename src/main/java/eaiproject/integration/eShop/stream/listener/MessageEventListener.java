/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package eaiproject.integration.eShop.stream.listener;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import eaiproject.integration.eShop.business.service.eShopService;
import eaiproject.integration.eShop.data.domain.Customer;
import eaiproject.integration.eShop.data.repository.CustomerRepository;
import eaiproject.integration.eShop.stream.message.EventMessage;
import eaiproject.integration.eShop.stream.message.OrderMessage;
import eaiproject.integration.eShop.stream.sender.MessageEventSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@EnableBinding(Sink.class)
public class MessageEventListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageEventSender messageEventSender;

    @Autowired
    private eShopService eShopService;
    

    private static Logger logger = LoggerFactory.getLogger(MessageEventListener.class);

    @StreamListener(target = Sink.INPUT,
            condition="(headers['type']?:'')=='UpdateLoyalityPoints'")
    @Transactional
    public void updateLoyalityPoints(@Payload EventMessage<OrderMessage> eventMessage) throws Exception {
        OrderMessage orderMessage = eventMessage.getPayload();
        logger.info("Payload received: "+ orderMessage.toString());
        Customer customer = eShopService.addLoyalityPoints(Integer.parseInt(orderMessage.getCustomerId()), Integer.parseInt(orderMessage.getLoyalityPoints()));
        orderMessage.setStatus("LoyalityPoints updated");
        messageEventSender.send(new EventMessage<>("UpdatePayment", orderMessage));
    }

}
