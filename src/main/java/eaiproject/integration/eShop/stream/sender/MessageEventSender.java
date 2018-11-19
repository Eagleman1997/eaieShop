/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package eaiproject.integration.eShop.stream.sender;

import com.fasterxml.jackson.databind.ObjectMapper;

import eaiproject.integration.eShop.stream.message.EventMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class MessageEventSender {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired @Output(Source.OUTPUT)
    private MessageChannel messageChannel;

    @Autowired
    ObjectMapper objectMapper;

    public void send(EventMessage<?> eventMessage) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(eventMessage);
            messageChannel.send(MessageBuilder.withPayload(jsonMessage).setHeader("type", eventMessage.getType()).build());
        } catch (Exception e) {
            throw new RuntimeException("Could not transform and send message due to: "+ e.getMessage(), e);
        }
    }
}
