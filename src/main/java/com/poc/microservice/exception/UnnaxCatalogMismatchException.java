package com.poc.microservice.exception;

public class UnnaxCatalogMismatchException extends RuntimeException{
    public UnnaxCatalogMismatchException(String exception) {
        super(exception);
    }
}
