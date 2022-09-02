package com.copilot.sample.repository;

import com.copilot.sample.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
    //create a customer row mapper to map the values from the result set to the customer object
    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(resultSet.getString("id"));
        customer.setCustomerName(resultSet.getString("name"));
        customer.setCustomerAddress(resultSet.getString("address"));
        customer.setCustomerPhone(resultSet.getString("phone"));
        customer.setCustomerEmail(resultSet.getString("email"));
        customer.setCustomerZipcode(resultSet.getString("zip"));
        customer.setCustomerCity(resultSet.getString("city"));
        customer.setCustomerState(resultSet.getString("state"));
        return customer;
    }

}
