package com.avalon.qrspringserver.advice.MenuAdvice;

import com.avalon.qrspringserver.error.MenuErrors.MenuNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

@ControllerAdvice
public class MenuNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(MenuNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Menu Not Found")
    public HashMap<String, String> message(MenuNotFound ex) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("status", "404");
        return map;
    }
}
