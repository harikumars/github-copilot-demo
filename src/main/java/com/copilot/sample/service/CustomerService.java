package com.copilot.sample.service;

import com.copilot.sample.exception.CustomerNotFoundException;
import com.copilot.sample.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer findById(String customerId) throws CustomerNotFoundException;

    Customer create(Customer customer);

    Customer update(Customer customer);

    void delete(Customer customer);

}
