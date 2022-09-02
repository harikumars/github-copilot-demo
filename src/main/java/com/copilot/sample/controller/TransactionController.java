package com.copilot.sample.controller;

import com.copilot.sample.model.Transaction;
import com.copilot.sample.repository.CustomerNotFoundException;
import com.copilot.sample.repository.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//annotate rest controller
@RestController
public class TransactionController {
    //autowired transactiondao
    @Autowired
    private TransactionDao transactionDao;


@PostMapping("/transaction")
    public void credit(Transaction transaction) {
    try {
        Transaction transaction1 =  transactionDao.credit(transaction);
    } catch (CustomerNotFoundException e) {
        throw new RuntimeException(e);
    }
}
    // debit post mapping method to create a new transaction only if the customer is listed in the repository
    @PostMapping("/transaction/debit")
    public void debit(Transaction transaction) {
        try {
            transactionDao.debit(transaction);
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // get balance post mapping method to get the current balance on the customer account
    @PostMapping("/transaction/getBalance/{customerId}")

    public Double getBalance(@PathVariable("customerId") String customerId) {
        return transactionDao.getBalance(customerId);
    }



}
