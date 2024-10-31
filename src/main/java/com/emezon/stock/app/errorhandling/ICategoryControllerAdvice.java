package com.emezon.stock.app.errorhandling;

import com.emezon.stock.domain.exceptions.category.*;

public interface ICategoryControllerAdvice<T> {

    Object handleCategoryNameAlreadyExistsException(CategoryNameAlreadyExistsException ex, T request);

    Object handleCategoryNameRequiredException(CategoryNameRequiredException ex, T request);

    Object handleCategoryDescriptionRequiredException(CategoryDescriptionRequiredException ex, T request);

    Object handleCategoryNameMaxLengthException(CategoryNameMaxLengthException ex, T request);

    Object handleCategoryDescriptionMaxLengthException(CategoryDescriptionMaxLengthException ex, T request);

    Object handleCategoryNotFoundByIdException(CategoryNotFoundByIdException ex, T request);

}
