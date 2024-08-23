package com.emezon.stock.app.errorhandling;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {
    private String errorMessage;
    private String requestedURI;
    private Integer errorCode;
    private String details;
    private LocalDateTime timestamp;

    public ExceptionResponse(String errorMessage, String requestedURI, Integer errorCode, String details) {
        this.errorMessage = errorMessage;
        this.requestedURI = requestedURI;
        this.errorCode = errorCode;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }
}
