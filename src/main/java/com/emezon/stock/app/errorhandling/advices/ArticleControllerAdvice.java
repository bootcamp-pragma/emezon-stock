package com.emezon.stock.app.errorhandling.advices;

import com.emezon.stock.app.errorhandling.ExceptionResponse;
import com.emezon.stock.domain.exceptions.article.ArticleCategoriesNumberInvalidException;
import com.emezon.stock.domain.exceptions.article.ArticleDuplicateCategoriesException;
import com.emezon.stock.domain.exceptions.article.ArticlePriceMinValueException;
import com.emezon.stock.domain.exceptions.article.ArticleStockMinValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ArticleControllerAdvice {

    @ExceptionHandler(ArticleCategoriesNumberInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleArticleCategoriesNumberInvalidException(ArticleCategoriesNumberInvalidException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(ArticleDuplicateCategoriesException.class)
    public ResponseEntity<ExceptionResponse> handleArticleDuplicateCategoriesException(ArticleDuplicateCategoriesException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(ArticlePriceMinValueException.class)
    public ResponseEntity<ExceptionResponse> handleArticlePriceMinValueException(ArticlePriceMinValueException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(ArticleStockMinValueException.class)
    public ResponseEntity<ExceptionResponse> handleArticleStockMinValueException(ArticleStockMinValueException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value(),
                ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

}
