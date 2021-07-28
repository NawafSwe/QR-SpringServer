package com.avalon.qrspringserver.advice.itemAdvice;

import com.avalon.qrspringserver.error.itemErrors.ItemNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

public class ItemNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ItemNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Item Not found")
    public HashMap<String, String> res(ItemNotFound ex) {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", HttpStatus.NOT_FOUND.name());
        map.put("reason", ex.getMessage());
        return map;
    }
}
