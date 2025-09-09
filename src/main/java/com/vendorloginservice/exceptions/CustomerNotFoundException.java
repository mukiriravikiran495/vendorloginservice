package com.vendorloginservice.exceptions;

public class CustomerNotFoundException extends AppException {
    public CustomerNotFoundException(String mobile) {
        super("Customer not found for mobile: " + mobile);
    }
}