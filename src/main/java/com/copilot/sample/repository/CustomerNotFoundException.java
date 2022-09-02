package com.copilot.sample.repository;

public class CustomerNotFoundException extends Throwable {
    public CustomerNotFoundException(String exMsg) {
        super(exMsg);
    }
}
