package com.company.FinalProject.exception;

public class CancelledOrderException extends RuntimeException{
    public CancelledOrderException(String message) {
        super(message);
    }
}
