package com.example.api.pregnancy.models;

public class ErrorModel {
    private String Message;
    private int httpErrorCode;

    public ErrorModel(String message) {
        Message = message;
    }

    public int getHttpErrorCode() {
        return httpErrorCode;
    }

    public void setHttpErrorCode(int httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
    }

    public ErrorModel(String message, int httpErrorCode) {
        Message = message;
        this.httpErrorCode = httpErrorCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
