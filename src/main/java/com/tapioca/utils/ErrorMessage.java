package com.tapioca.utils;

public enum ErrorMessage {
    EMPLOYEE_EXISTS("Employee already exists");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
