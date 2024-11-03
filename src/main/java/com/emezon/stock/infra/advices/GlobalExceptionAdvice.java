package com.emezon.stock.infra.advices;

import com.emezon.stock.app.errorhandling.IGlobalExceptionHandler;
import com.emezon.stock.domain.utils.ExceptionResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(20)
public class GlobalExceptionAdvice implements IGlobalExceptionHandler<WebRequest> {

    @Override
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneralException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage() != null ? ex.getMessage() : "Ha ocurrido un error inesperado.";

        ExceptionResponse response = new ExceptionResponse(
                message,
                request.getDescription(false),
                status.value()
        );

        return new ResponseEntity<>(response, status);
    }

}