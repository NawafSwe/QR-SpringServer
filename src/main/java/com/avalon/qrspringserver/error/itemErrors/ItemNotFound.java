package com.avalon.qrspringserver.error.itemErrors;

public class ItemNotFound extends RuntimeException{
    private final String message;

    public ItemNotFound(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
