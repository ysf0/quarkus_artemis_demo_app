package com.demo.service;

import com.demo.dto.Content;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Session;

@ApplicationScoped
public class ContentProducer {

    @Inject
    ConnectionFactory connectionFactory;

    public void sendContent(Content content) {
        try {
            JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);
            context.createProducer().send(context.createQueue("content"), content.getContentData());
            System.out.println("data sent to artemis");
        } catch (JMSRuntimeException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}