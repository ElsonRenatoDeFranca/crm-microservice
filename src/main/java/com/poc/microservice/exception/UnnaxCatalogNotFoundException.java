package com.poc.microservice.exception;

public class UnnaxCatalogNotFoundException extends RuntimeException{
    public UnnaxCatalogNotFoundException(String exception) {
        super(exception);
    }
}
