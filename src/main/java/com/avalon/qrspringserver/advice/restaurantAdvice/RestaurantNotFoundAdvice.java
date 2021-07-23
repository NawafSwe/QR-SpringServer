package com.avalon.qrspringserver.advice.restaurantAdvice;

import com.avalon.qrspringserver.error.restaurantErrors.RestaurantNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.HashMap;

@ControllerAdvice
public class RestaurantNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(RestaurantNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "restaurant could not be found")
    HashMap<String, String> message() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Restaurant Not found");
        map.put("status", HttpStatus.NOT_FOUND.name());
        return map;
    }
}
