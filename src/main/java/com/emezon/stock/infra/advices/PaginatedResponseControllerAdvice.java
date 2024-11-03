package com.emezon.stock.infra.advices;

import com.emezon.stock.app.errorhandling.IPaginatedResponseControllerAdvice;
import com.emezon.stock.domain.utils.ExceptionResponse;
import com.emezon.stock.domain.constants.PaginatedResponseErrorMessages;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponsePageNumberInvalidException;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponsePageSizeInvalidException;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponseSortDirectionInvalidException;
import org.springframework.core.annotation.Order;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(10)
public class PaginatedResponseControllerAdvice implements IPaginatedResponseControllerAdvice<WebRequest> {

    @Override
    @ExceptionHandler(PaginatedResponsePageNumberInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryPageNumberInvalidException(PaginatedResponsePageNumberInvalidException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                PaginatedResponseErrorMessages.PAGE_NUMBER_INVALID);
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(PaginatedResponsePageSizeInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryPageSizeInvalidException(PaginatedResponsePageSizeInvalidException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                PaginatedResponseErrorMessages.PAGE_SIZE_INVALID);
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(PaginatedResponseSortDirectionInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleCategorySortDirectionInvalidException(PaginatedResponseSortDirectionInvalidException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                PaginatedResponseErrorMessages.SORT_DIRECTION_INVALID);
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ExceptionResponse> handlePropertyReferenceException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

}
