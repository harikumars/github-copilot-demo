package com.copilot.sample.configuration;

import com.copilot.sample.controller.TradesController;
import com.copilot.sample.controller.TransactionController;
import com.copilot.sample.model.Trades;
import com.copilot.sample.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    
    //autowire the trades controller
    @Autowired
    private TradesController tradesController;

    //send a post mapping method and @request body param as Transaction object and return a message
    @PostMapping
    public String sendMessageWithReply(@RequestBody Trades trades) {



        //send the message to the queue
        jmsTemplate.convertAndSend(queue, trades);
        //return a message
        return "Message sent to the Queue Successfully";
    }/*
    @PostMapping
    public void sendMessage(@RequestBody Transaction transaction) {
        //send the transaction object to the queue
        jmsTemplate.convertAndSend(queue, transaction);
    }*/

}
