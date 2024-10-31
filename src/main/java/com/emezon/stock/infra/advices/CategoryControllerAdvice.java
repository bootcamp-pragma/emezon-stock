package com.emezon.stock.infra.advices;

import com.emezon.stock.app.errorhandling.ICategoryControllerAdvice;
import com.emezon.stock.domain.utils.ExceptionResponse;
import com.emezon.stock.domain.constants.CategoryErrorMessages;
import com.emezon.stock.domain.exceptions.category.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CategoryControllerAdvice implements ICategoryControllerAdvice<WebRequest> {

    @Override
    @ExceptionHandler(CategoryNameAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNameAlreadyExistsException(CategoryNameAlreadyExistsException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                CategoryErrorMessages.CATEGORY_ALREADY_EXISTS_DETAILS);
        return new ResponseEntity<>(response,  status);
    }

    @Override
    @ExceptionHandler(CategoryNameRequiredException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNameRequiredException(CategoryNameRequiredException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(CategoryDescriptionRequiredException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryDescriptionRequiredException(CategoryDescriptionRequiredException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(CategoryNameMaxLengthException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNameMaxLengthException(CategoryNameMaxLengthException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(CategoryDescriptionMaxLengthException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryDescriptionMaxLengthException(CategoryDescriptionMaxLengthException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(CategoryNotFoundByIdException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNotFoundByIdException(CategoryNotFoundByIdException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

}
