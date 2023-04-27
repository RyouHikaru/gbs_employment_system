package com.tapioca.utils;

public enum ErrorMessage {
    EMPLOYEE_EXISTS("Employee already exists"),
    INVALID_EMPLOYEE_ID("Invalid Employee ID"),
    INVALID_SALARY("Employee has already a salary for the specified month"),
    INVALID_AMOUNT("Amount should be greater than zero"),
    AMOUNT_IS_ZERO("Amount should not be equal to zero"),
    MISSING_DESCRIPTION("Description is required for this type"),
    NULL_START_DATE("Start date is required"),
    NULL_END_DATE("End date is required"),
    INVALID_START_DATE("Start date should be before end date");

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
