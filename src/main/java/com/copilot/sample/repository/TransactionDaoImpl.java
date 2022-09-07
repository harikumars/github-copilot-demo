package com.copilot.sample.repository;

import com.copilot.sample.model.Customer;
import com.copilot.sample.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//annotate with repository
@Repository



public class TransactionDaoImpl implements TransactionDao {
    //autowired annotation for customer dao
    @Autowired
    private CustomerDao customerDao;

    //aitwre jdbctemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Accept Debit transaction
    @Override
public Transaction debit(Transaction transaction) throws CustomerNotFoundException {
        //get the customer object from the customer dao
        Customer customer = customerDao.findById(transaction.getCustomerId());
        //check if the customer is found
        if (customer != null) {
            //create a sql query to get the amount in the transaction object for a customer
            String sql = "SELECT amount FROM transaction WHERE customerId = ? ORDER BY id DESC LIMIT 1";
            //get the amount from the transaction object
            Double amount = jdbcTemplate.queryForObject(sql, new Object[]{transaction.getCustomerId()}, Double.class);
            //check if the amount is greater than 101

            if (amount >= 101) {
                //subtract the amount from the transaction object
                Double newAmount= amount - Double.valueOf(transaction.getTransactionAmount());

                //create a sql query to insert the transaction customer id,date,type,description and above amount in the repository for a customer
                String sql1 = "INSERT INTO transaction (customerId,date,type,description,amount) VALUES (?,?,?,?,?)";
                //call the update method to insert the transaction customer id,date,type,description and above amount in the repository for a customer
                jdbcTemplate.update(sql1, transaction.getCustomerId(), transaction.getTransactionDate(),"DR", transaction.getDescription(), newAmount);
                //return the transaction object after the insert
                return transaction;
                } else {
                //throw an exception if the amount is less than 0
                throw new CustomerNotFoundException("No sufficient balance");
            }


        }
        else {
            //throw an exception if the customer is not found
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Override
    public Transaction credit(Transaction transaction) throws CustomerNotFoundException {
        //get the customer object from the customer dao
        Customer customer = customerDao.findById(transaction.getCustomerId());
        //check if the customer is found
        if (customer != null) {
            //create a sql query to get the amount in the transaction object for a customer
            String sql = "SELECT amount FROM transaction WHERE customerId = ? ORDER BY id DESC LIMIT 1";
            //get the amount from the transaction object using the transactionrowmapper

            //surround with try catch block
            try {
                Double amount = jdbcTemplate.queryForObject(sql, new Object[]{transaction.getCustomerId()}, Double.class);
                //check if the amount is greater than 0
                if (amount >= 0) {
                    //add the amount from the transaction object
                    Double newAmount= amount
                            + Double.valueOf(transaction.getTransactionAmount());

              //create a sql query to insert the transaction customer id,date,type,description and above amount in the repository for a customer
                String sql1 = "INSERT INTO transaction (customerId,date,type,description,amount) VALUES (?,?,?,?,?)";
                //call the update method to insert the transaction customer id,date,type,description and above amount in the repository for a customer
                jdbcTemplate.update(sql1, transaction.getCustomerId(), transaction.getTransactionDate(), "CR", transaction.getDescription(), newAmount);
                //return the transaction object after the insert
                return transaction;

            }
            } catch (EmptyResultDataAccessException e) {
                //create a sql query to insert the transaction customer id,date,type,description and above amount in the repository for a customer
                String sql1 = "INSERT INTO transaction (customerId,date,type,description,amount) VALUES (?,?,?,?,?)";
                //call the update method to insert the transaction customer id,date,type,description and above amount in the repository for a customer
                jdbcTemplate.update(sql1, transaction.getCustomerId(), transaction.getTransactionDate(), "CR", transaction.getDescription(), transaction.getTransactionAmount());
                //return the transaction object after the insert
                return transaction;
            }


        }
        else {
            //throw an exception if the customer is not found
            throw new CustomerNotFoundException("Customer not found");
        }
        return transaction;
    }

    @Override
    public Double getBalance(String customerId) {
        //get the balance from the transaction object with the latest transaction id
String sql = "SELECT amount FROM transaction WHERE customerId = ? ORDER BY id DESC LIMIT 1";
        //get the balance from the transaction object
Double amount = jdbcTemplate.queryForObject(sql, new Object[]{customerId}, Double.class);
    //return the balance from the transaction object
return amount;
    }
}
