package com.copilot.sample.repository;

import com.copilot.sample.model.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> findAll();

    Customer findById(String customerId);

    Customer create(Customer customer);

    Customer update(Customer customer);

    void delete(Customer customer);

}
