package com.copilot.sample.controller;

//annotate rest controller
@RestController
public class CustomerController {

    //autowire customer repository
    @Autowired
    private CustomerRepository customerRepository;

    //annotation to define get method
    @GetMapping("/customer")
    public Customer getCustomer() {
        return customerRepository.findById("1").get();
    }

    //annotation to define post method
    @PostMapping("/customer")
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    //annotation to define put method
    @PutMapping("/customer")
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    //annotation to define delete method
    @DeleteMapping("/customer")
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    //annotation to define get method
    @GetMapping("/customer/{customerId}")
    public Customer getCustomer(@PathVariable String customerId) {
        return customerRepository.findById(customerId).get();
    }


}
