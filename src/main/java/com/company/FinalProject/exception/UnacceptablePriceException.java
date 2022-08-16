package com.company.FinalProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnacceptablePriceException extends RuntimeException{
    public UnacceptablePriceException(String message) {
        super(message);
    }
}
