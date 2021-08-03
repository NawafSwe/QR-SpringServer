package com.avalon.qrspringserver.error.userErrors;

public class UserNotAuthenticated extends RuntimeException {
    private final String message;

    public UserNotAuthenticated(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

