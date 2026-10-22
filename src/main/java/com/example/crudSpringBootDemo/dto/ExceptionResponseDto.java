package com.example.crudSpringBootDemo.dto;

import java.time.LocalDateTime;

public class ExceptionResponseDto {
    private LocalDateTime timestamp;
    private int statusCode;
    private String path;
    private String error;
    private String message;

    public ExceptionResponseDto(LocalDateTime timestamp, int statusCode, String path, String error, String message) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.path = path;
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
