package com.copilot.sample.repository;


public interface CustomerRepository extends CrudRepository<Customer, String> {

    //find by id
    Customer findById(String customerId);
    //find by name
    List<Customer> findByName(String customerName);
    //find by name and phone
    List<Customer> findByNameAndPhone(String customerName, String customerPhone);



}
