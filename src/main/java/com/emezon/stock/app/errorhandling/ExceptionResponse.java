package com.emezon.stock.app.errorhandling;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse {
    private String message;
    private String requestedURI;
    private Integer code;
    private LocalDateTime timestamp;

    public ExceptionResponse(String message, String requestedURI, Integer code) {
        this.message = message;
        this.requestedURI = requestedURI;
        this.code = code;
        this.timestamp = LocalDateTime.now();
    }
}
