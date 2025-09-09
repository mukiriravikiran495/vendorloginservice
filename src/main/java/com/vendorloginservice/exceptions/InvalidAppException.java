package com.vendorloginservice.exceptions;

public class InvalidAppException extends AppException {
    public InvalidAppException(String appId) {
        super("Invalid or inactive APPID: " + appId);
    }
}