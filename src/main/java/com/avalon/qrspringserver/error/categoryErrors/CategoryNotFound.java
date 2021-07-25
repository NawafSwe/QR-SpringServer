package com.avalon.qrspringserver.error.categoryErrors;

public class CategoryNotFound extends RuntimeException {

    private final String message;

    public CategoryNotFound(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
