package com.copilot.sample.configuration;

import com.copilot.sample.controller.TransactionController;
import com.copilot.sample.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;


//add rest controller annotation
@RestController
//add request mapping annotation
@RequestMapping("/mqProducer")
public class MQProducer {

    //autowire jmstemplate
    @Autowired
    private JmsTemplate jmsTemplate;
    // autowire the queue
    @Autowired
    private Queue queue;
    
    //autowire the transaction controller
    @Autowired
    private TransactionController transactionController;

    //send a post mapping method and @request body param as Transaction object and return a message
    @PostMapping
    public String sendMessageWithReply(@RequestBody Transaction transaction) {


        if (transaction.getTransactionType().equals("CR")) {
            //call the transaction controller to create a new transaction
            transactionController.credit(transaction);
        }
        // check if the balance is greater than 100 and check if transaction type is debit else send a message saying as insufficient balance
        else if (transaction.getTransactionType().equals("DR")) {
            if (transactionController.getBalance(transaction.getCustomerId()) > 100) {
                //call the transaction controller to create a new transaction
                transactionController.debit(transaction);
            } else {
                return "Debit Amount > 100";
            }
        }
        //check if transaction type is bal
        else if (transaction.getTransactionType().equals("BAL")) {
            //call the transaction controller to get the current balance on the customer account
            transactionController.getBalance(transaction.getCustomerId());
        }
        //send the message to the queue
        jmsTemplate.convertAndSend(queue, transaction);
        //return a message
        return "Message sent to the Queue Successfully";
    }/*
    @PostMapping
    public void sendMessage(@RequestBody Transaction transaction) {
        //send the transaction object to the queue
        jmsTemplate.convertAndSend(queue, transaction);
    }*/

}
