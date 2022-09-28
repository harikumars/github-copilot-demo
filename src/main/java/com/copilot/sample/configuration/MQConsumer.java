package com.copilot.sample.configuration;

import com.copilot.sample.controller.TradesController;
import com.copilot.sample.controller.TransactionController;
import com.copilot.sample.model.Trades;
import com.copilot.sample.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

//add component
@Component
public class MQConsumer {
//add a jms consumer
    //autowire the transaction controller
    @Autowired
    private TradesController tradesController;

    @JmsListener(destination = "spring-activemq-queue")
    public void receiveMessage(Trades trades) {
        //call the transaction controller to create the transaction
        tradesController.createTrades(trades);
        System.out.println("Received <" + trades.toString() + ">");


    }
}
