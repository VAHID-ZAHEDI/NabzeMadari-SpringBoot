package com.example.api.pregnancy.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExternalDependencyException extends RuntimeException {

    public ExternalDependencyException() {
        super("External Dependency Failed");
    }
    public ExternalDependencyException(String message) {
        super(message);
    }

}