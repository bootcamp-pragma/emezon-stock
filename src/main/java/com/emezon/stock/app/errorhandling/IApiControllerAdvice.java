package com.emezon.stock.app.errorhandling;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

public interface IApiControllerAdvice<T> {

    Object handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, T request);

    Object handleConstraintViolationException(HandlerMethodValidationException ex, T request);

}
