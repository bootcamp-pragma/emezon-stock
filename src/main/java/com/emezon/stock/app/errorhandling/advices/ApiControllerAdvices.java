package com.emezon.stock.app.errorhandling.advices;

import com.emezon.stock.app.errorhandling.ExceptionResponse;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

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

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(HandlerMethodValidationException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errorMessages = ex.getAllValidationResults().stream().map(ParameterValidationResult::getResolvableErrors)
                .flatMap(List::stream).map(MessageSourceResolvable::getDefaultMessage).toList();
        String message = String.join(",\n ", errorMessages);
        ExceptionResponse response = new ExceptionResponse(
                message,
                request.getDescription(false),
                status.value(),
                message);
        return new ResponseEntity<>(response, status);
    }


//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleGenericException(Exception ex) {
//        // Puedes usar ex.getClass().getName() para identificar el tipo exacto de la excepción
//        return new ResponseEntity<>("Unhandled Exception: " + ex.getClass().getName() + " - " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }


}
