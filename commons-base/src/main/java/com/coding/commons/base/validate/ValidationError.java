package com.coding.commons.base.validate;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {

    private List<String> errors = new ArrayList<>();
    private String errorMessage;

    public ValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addValidationError(String error) {
        errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
