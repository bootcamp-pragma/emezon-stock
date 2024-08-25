package com.emezon.stock.app.errorhandling.advices;

import com.emezon.stock.app.errorhandling.ExceptionResponse;
import com.emezon.stock.domain.common.constants.CategoryErrorMessages;
import com.emezon.stock.domain.exceptions.category.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CategoryControllerAdvice {

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

    @ExceptionHandler(CategoryCodeAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryCodeAlreadyExistsException(CategoryCodeAlreadyExistsException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                CategoryErrorMessages.CATEGORY_CODE_ALREADY_EXISTS_DETAILS);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(CategoryNameRequiredException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNameRequiredException(CategoryNameRequiredException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                CategoryErrorMessages.CATEGORY_NAME_REQUIRED);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(CategoryCodeRequiredException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryCodeRequiredException(CategoryCodeRequiredException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                CategoryErrorMessages.CATEGORY_CODE_REQUIRED);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(CategoryDescriptionRequiredException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryDescriptionRequiredException(CategoryDescriptionRequiredException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                CategoryErrorMessages.CATEGORY_DESCRIPTION_REQUIRED);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(CategoryNameMaxLengthException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNameMaxLengthException(CategoryNameMaxLengthException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(CategoryDescriptionMaxLengthException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryDescriptionMaxLengthException(CategoryDescriptionMaxLengthException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(CategoryPageNumberInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryPageNumberInvalidException(CategoryPageNumberInvalidException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                CategoryErrorMessages.CATEGORY_PAGE_NUMBER_INVALID);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(CategoryPageSizeInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryPageSizeInvalidException(CategoryPageSizeInvalidException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                CategoryErrorMessages.CATEGORY_PAGE_SIZE_INVALID);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(CategorySortDirectionInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleCategorySortDirectionInvalidException(CategorySortDirectionInvalidException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                CategoryErrorMessages.CATEGORY_SORT_DIRECTION_INVALID);
        return new ResponseEntity<>(response, status);
    }

}
