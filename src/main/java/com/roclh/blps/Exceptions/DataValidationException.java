package com.roclh.blps.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Validation error")
public class DataValidationException extends Exception{
    public DataValidationException(String message) {
        super(message);
    }
}
