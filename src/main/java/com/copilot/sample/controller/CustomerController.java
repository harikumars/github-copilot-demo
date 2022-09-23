package com.copilot.sample.controller;

import com.copilot.sample.configuration.ServiceResponse;
import com.copilot.sample.model.Customer;
import com.copilot.sample.repository.CustomerDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//annotate rest controller
@RestController
public class CustomerController {

    //autowire customer repository
    @Autowired
    private CustomerDao customerRepository;

    //annotation to define get method
    @GetMapping("/customer")
    public Customer getCustomer() {
        return customerRepository.findById("1");
    }

    //annotation to define get method for all customers
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
    //annotation to define postmapping and body parameter
    @PostMapping(value = "/createCustomer", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ServiceResponse> createCustomer(@RequestBody Customer customer) {
        //validate the customer  phone and email with the list of customer object in the repository
        for (Customer c : customerRepository.findAll()) {
            if (c.getCustomerPhone().equals(customer.getCustomerPhone())) {
                throw new IllegalArgumentException("Customer Phone already exists");
            }
        }
        //call the create method to create a new customer
        Customer cust= customerRepository.create(customer);
        //return the response entity after creating the customer
        return ResponseEntity.ok(new ServiceResponse("success","Customer added", cust));


    }



    //annotation to define put method and body parameter
    @PutMapping("/customer")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerRepository.update(customer);
    }

    //annotation to define delete method for a particular id using path variable
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable String id) {
        Customer customer = customerRepository.findById(id);
        customerRepository.delete(customer);
    }

    //annotation to define get method for a particular customerId
    @GetMapping("/customer/{customerId}")
    public Customer getCustomer(@PathVariable String customerId) {
        return customerRepository.findById(customerId);
    }


}
