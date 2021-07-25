package com.avalon.qrspringserver.advice.categoryAdvice;

import com.avalon.qrspringserver.error.categoryErrors.CategoryNotFound;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

@ControllerAdvice
public class CategoryNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CategoryNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Category was not found")
    public ResponseEntity<?> response() {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", HttpStatus.NOT_FOUND.name());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Not Found Error")
                        .withDetail("Category was not found")
                        .withProperties(map)
                );
    }
}
