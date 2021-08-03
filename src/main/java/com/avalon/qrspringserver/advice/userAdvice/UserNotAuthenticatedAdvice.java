package com.avalon.qrspringserver.advice.userAdvice;

import com.avalon.qrspringserver.error.userErrors.UserDuplicatedEmail;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserNotAuthenticatedAdvice {

    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User unauthorized please login")
    public Map<String, String> res(UserDuplicatedEmail ex) {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", HttpStatus.UNAUTHORIZED.name());
        map.put("message", ex.getMessage());
        map.put("reason", "User unauthorized please login");
        return map;

    }
}
