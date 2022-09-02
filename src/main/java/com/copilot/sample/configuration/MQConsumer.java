package com.copilot.sample.configuration;

import com.copilot.sample.controller.TransactionController;
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
    private TransactionController transactionController;

    @JmsListener(destination = "spring-activemq-queue")
    public void receiveMessage(Transaction transaction) {
        // check if transaction type is credit
        if (transaction.getTransactionType().equals("CR")) {
            //call the transaction controller to create a new transaction

            transactionController.credit(transaction);
        }
        // check if transaction type is debit
        else if (transaction.getTransactionType().equals("DR")) {
            //call the transaction controller to create a new transaction
            transactionController.debit(transaction);
        }
        //check if transaction type is bal
        else if (transaction.getTransactionType().equals("BAL")) {
            //call the transaction controller to get the current balance on the customer account
            transactionController.getBalance(transaction.getCustomerId());
        }



    }
}
