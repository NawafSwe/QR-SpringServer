package com.avalon.qrspringserver.error.userErrors;

public class UserDuplicatedEmail extends RuntimeException {
    private final String message;

    public UserDuplicatedEmail(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
