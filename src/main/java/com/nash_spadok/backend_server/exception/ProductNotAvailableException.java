package com.nash_spadok.backend_server.exception;


public class ProductNotAvailableException extends RuntimeException{
    public ProductNotAvailableException(String message) {
        super(message);
    }
}
