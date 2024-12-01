package com.poc.microservice.exception;

public class UnnaxTransactionNotFoundException extends RuntimeException{
    public UnnaxTransactionNotFoundException(String exception) {
        super(exception);
    }
}
