package com.company.FinalProject.exception;

public class UserIsNotAllowedException extends RuntimeException{
    public UserIsNotAllowedException(String message) {
        super(message);
    }
}
