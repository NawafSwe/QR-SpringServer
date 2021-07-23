package com.avalon.qrspringserver.error.MenuErrors;

public class MenuNotFound extends RuntimeException{
    private final String message;
    public MenuNotFound(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
