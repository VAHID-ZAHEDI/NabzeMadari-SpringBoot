package com.example.api.pregnancy.models;

public class ErrorModel {
    String Message;

    public ErrorModel(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
