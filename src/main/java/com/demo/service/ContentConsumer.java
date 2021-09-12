package com.demo.service;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class ContentConsumer implements Runnable {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    @Inject
    ConnectionFactory connectionFactory;

    void onStart(@Observes StartupEvent ev) {
        scheduler.scheduleWithFixedDelay(this, 0L, 10L, TimeUnit.SECONDS);
    }

    void onStop(@Observes ShutdownEvent ev) {
        scheduler.shutdown();
    }

    @Override
    public void run() {
        boolean connected = false;
        JMSContext context = null;
        while (!connected) {
            try {
                Thread.sleep(3000);
                context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);
                connected = true;
            } catch (JMSSecurityRuntimeException e) {
                System.out.println("JMSSecurityRuntimeException - Fatal error.");
                return;
            } catch (JMSRuntimeException e) {
                System.out.println("Artemis is not up yet! retrying...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Connected to Artemis");
        javax.jms.JMSConsumer consumer = context.createConsumer(context.createQueue("content"));
        while (true) {
            Message message = consumer.receive();
            if (message == null) {
                return;
            }
            try {
                System.out.println("Message (content data) received from Artemis: " + message.getBody(String.class));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}