package com.avalon.qrspringserver.advice.restaurantAdvice;

import com.avalon.qrspringserver.error.restaurantErrors.RestaurantDuplicatedEmail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

@ControllerAdvice
public class RestaurantDuplicatedEmailAdvice {
    @ResponseBody
    @ExceptionHandler(RestaurantDuplicatedEmail.class)
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Restaurant Email is Already in use")
    HashMap<String, String> message(String message) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", message);
        map.put("status", "409");
        map.put("StatusText", HttpStatus.CONFLICT.name());
        return map;

    }
}
