package com.avalon.qrspringserver.utils;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public class ServerResponse<T> {
    private final T data;
    private final int status;
    private final List<?> errors;

    public ServerResponse(T data, int status, List<?> errors) {
        this.data = data;
        this.status = status;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public List<?> getErrors() {
        return errors;
    }

    public T getData() {
        return data;
    }


    @Override
    public String toString() {
        return "ServerResponse{" +
                "data=" + data +
                ", status=" + status +
                ", errors=" + errors +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, status, errors);
    }

    public ResponseEntity<ServerResponse> sendResponse() {
        return ResponseEntity
                .status(this.status)
                .body(this);
    }
}
