package com.avalon.qrspringserver.advice.userAdvice;

import com.avalon.qrspringserver.error.userErrors.UserDuplicatedEmail;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserDuplicatedAdvice {

    @ResponseBody
    @ExceptionHandler(UserDuplicatedEmail.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User email is already exits")
    public Map<String, String> res(UserDuplicatedEmail ex) {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", HttpStatus.BAD_REQUEST.name());
        map.put("message", ex.getMessage());
        map.put("reason", "User email is already exits");
        return map;

    }
}
