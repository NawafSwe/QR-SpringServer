package com.avalon.qrspringserver.error.restaurantErrors;

public class RestaurantNotFound extends RuntimeException {
    private final String message;

    public RestaurantNotFound(String message) {
        super(message);
        this.message = message;

    }

    @Override
    public String getMessage() {
        return message;
    }
}
