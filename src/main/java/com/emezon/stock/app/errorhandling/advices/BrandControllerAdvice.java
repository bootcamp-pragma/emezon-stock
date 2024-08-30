package com.emezon.stock.app.errorhandling.advices;

import com.emezon.stock.app.errorhandling.ExceptionResponse;
import com.emezon.stock.domain.constants.BrandErrorMessages;
import com.emezon.stock.domain.exceptions.brand.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BrandControllerAdvice {

    @ExceptionHandler(BrandNameRequiredException.class)
    public ResponseEntity<ExceptionResponse> handleBrandNameRequiredException(BrandNameRequiredException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                BrandErrorMessages.BRAND_NAME_REQUIRED);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(BrandNameAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleBrandNameAlreadyExistsException(BrandNameAlreadyExistsException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                BrandErrorMessages.BRAND_NAME_ALREADY_EXISTS_DETAILS);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(BrandNameMaxLengthException.class)
    public ResponseEntity<ExceptionResponse> handleBrandNameMaxLengthException(BrandNameMaxLengthException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(BrandDescriptionRequiredException.class)
    public ResponseEntity<ExceptionResponse> handleBrandDescriptionRequiredException(BrandDescriptionRequiredException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                BrandErrorMessages.BRAND_DESCRIPTION_REQUIRED);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(BrandDescriptionMaxLengthException.class)
    public ResponseEntity<ExceptionResponse> handleBrandDescriptionMaxLengthException(BrandDescriptionMaxLengthException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

}
