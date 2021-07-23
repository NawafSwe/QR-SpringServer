package com.avalon.qrspringserver.error;


public class ServerError extends RuntimeException {
    private final String message;

    public ServerError(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
