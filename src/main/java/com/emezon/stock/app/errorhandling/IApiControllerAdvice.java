package com.emezon.stock.app.errorhandling;

public interface IApiControllerAdvice<T> {

    Object handleMethodArgumentNotValidException(Exception ex, T request);

    Object handleConstraintViolationException(Exception ex, T request);

    Object handleInvalidFormatException(Exception ex, T request);

    Object handleIllegalArgumentException(Exception ex, T request);

    Object handleAuthorizationDeniedException(Exception ex, T request);

}
