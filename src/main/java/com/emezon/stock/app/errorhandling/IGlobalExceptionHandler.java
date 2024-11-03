package com.emezon.stock.app.errorhandling;

public interface IGlobalExceptionHandler<T> {

    Object handleGeneralException(Exception ex, T request);

}