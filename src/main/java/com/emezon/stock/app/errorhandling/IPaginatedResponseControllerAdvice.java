package com.emezon.stock.app.errorhandling;

import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponsePageNumberInvalidException;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponsePageSizeInvalidException;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponseSortDirectionInvalidException;

public interface IPaginatedResponseControllerAdvice<T> {

    Object handleCategoryPageNumberInvalidException(PaginatedResponsePageNumberInvalidException ex, T request);

    Object handleCategoryPageSizeInvalidException(PaginatedResponsePageSizeInvalidException ex, T request);

    Object handleCategorySortDirectionInvalidException(PaginatedResponseSortDirectionInvalidException ex, T request);

    Object handlePropertyReferenceException(Exception ex, T request);

}
