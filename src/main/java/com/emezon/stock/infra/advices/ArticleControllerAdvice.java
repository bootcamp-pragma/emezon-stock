package com.emezon.stock.infra.advices;

import com.emezon.stock.app.errorhandling.IArticleControllerAdvice;
import com.emezon.stock.domain.exceptions.article.*;
import com.emezon.stock.domain.utils.ExceptionResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(1)
public class ArticleControllerAdvice implements IArticleControllerAdvice<WebRequest> {

    @Override
    @ExceptionHandler(ArticleCategoriesNumberInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleArticleCategoriesNumberInvalidException(ArticleCategoriesNumberInvalidException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(ArticleDuplicateCategoriesException.class)
    public ResponseEntity<ExceptionResponse> handleArticleDuplicateCategoriesException(ArticleDuplicateCategoriesException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(ArticlePriceMinValueException.class)
    public ResponseEntity<ExceptionResponse> handleArticlePriceMinValueException(ArticlePriceMinValueException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(ArticleStockMinValueException.class)
    public ResponseEntity<ExceptionResponse> handleArticleStockMinValueException(ArticleStockMinValueException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

    @Override
    public Object handleArticleNotFoundByIdException(ArticleNotFoundByIdException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

}
