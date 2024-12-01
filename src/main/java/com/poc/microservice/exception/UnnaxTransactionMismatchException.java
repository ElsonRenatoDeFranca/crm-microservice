package com.poc.microservice.exception;

public class UnnaxTransactionMismatchException extends RuntimeException{
    public UnnaxTransactionMismatchException(String exception) {
        super(exception);
    }
}
