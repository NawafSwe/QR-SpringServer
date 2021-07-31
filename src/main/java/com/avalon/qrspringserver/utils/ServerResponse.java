package com.avalon.qrspringserver.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class ServerResponse<T> {
    private final T data;
    private final HttpStatus status;
    private final LinkedList<String> errors;


    public ServerResponse(T data, HttpStatus status) {
        this.data = data;
        this.status = status;
        this.errors = new LinkedList<String>();


    }

    public HttpStatus getStatus() {
        return status;
    }

    public LinkedList<String> getErrors() {
        return errors;
    }

    public T getData() {
        return data;
    }


    @Override
    public String toString() {
        return "{" +
                "data:" + data +
                ", status:" + status +
                ", errors=" + errors +

                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, status, errors);
    }

    public ResponseEntity<?> sendResponse() {
        return ResponseEntity
                .status(this.status)
                .body(this);
    }
}
