package com.emezon.stock.app.errorhandling.advices;

import com.emezon.stock.app.errorhandling.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@ControllerAdvice
public class ApiControllerAdvices {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errorsMessages = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).toList();
        String messages = String.join(",\n ", errorsMessages);
        ExceptionResponse response = new ExceptionResponse(
                messages,
                request.getDescription(false),
                status.value(),
                messages);
        return new ResponseEntity<>(response, status);
    }

}
