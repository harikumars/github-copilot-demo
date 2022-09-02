package com.copilot.sample.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
//EnableJms
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@Configuration
@EnableJms
public class SpringActiveMqConfig {
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        return activeMQConnectionFactory;
    }
    //enable jms temptate
    @Bean
public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory());
        //set the message converter for mapping jackson to jms message
        jmsTemplate.setMessageConverter(messageConverter());

        return jmsTemplate;
    }
    //create a Active MQ queue object
    @Bean
    public Queue activeMQQueue() {
        return new ActiveMQQueue("spring-activemq-queue");
    }
    //create a message converter for jackson and set the mediatype to json
    @Bean
public org.springframework.jms.support.converter.MessageConverter messageConverter() {
        //set the property name for the message converter to use the json message converter
        org.springframework.jms.support.converter.MappingJackson2MessageConverter messageConverter = new org.springframework.jms.support.converter.MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_type");
        return messageConverter;
    }
}


