package com.avalon.qrspringserver.error.restaurantErrors;

public class RestaurantDuplicatedEmail extends RuntimeException {
    private final String message;

    public RestaurantDuplicatedEmail(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
