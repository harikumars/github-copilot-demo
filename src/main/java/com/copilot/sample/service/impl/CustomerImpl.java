package com.copilot.sample.service.impl;

import com.copilot.sample.model.Customer;

public class CustomerImpl {


    //define create Customer method with validation and return Customer object
    public Customer createCustomer(Customer customer) {

        if (customer.getCustomerName() == null || customer.getCustomerName().isEmpty()) {
            throw new IllegalArgumentException("Customer name is required");
        }
        if (customer.getCustomerAddress() == null || customer.getCustomerAddress().isEmpty()) {
            throw new IllegalArgumentException("Customer address is required");
        }
        if (customer.getCustomerPhone() == null || customer.getCustomerPhone().isEmpty()) {
            throw new IllegalArgumentException("Customer phone is required");
        }
        if (customer.getCustomerEmail() == null || customer.getCustomerEmail().isEmpty()) {
            throw new IllegalArgumentException("Customer email is required");
        }
        return customer;
    }






}
