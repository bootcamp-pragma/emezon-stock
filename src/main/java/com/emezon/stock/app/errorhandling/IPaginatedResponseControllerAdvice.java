package com.emezon.stock.app.errorhandling;

import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponsePageNumberInvalidException;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponsePageSizeInvalidException;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponseSortDirectionInvalidException;
import com.emezon.stock.domain.utils.ExceptionResponse;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public interface IPaginatedResponseControllerAdvice<T> {

    Object handleCategoryPageNumberInvalidException(PaginatedResponsePageNumberInvalidException ex, T request);

    Object handleCategoryPageSizeInvalidException(PaginatedResponsePageSizeInvalidException ex, T request);

    Object handleCategorySortDirectionInvalidException(PaginatedResponseSortDirectionInvalidException ex, T request);

    Object handlePropertyReferenceException(Exception ex, T request);

}
