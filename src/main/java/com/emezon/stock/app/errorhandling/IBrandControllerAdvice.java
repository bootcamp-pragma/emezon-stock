package com.emezon.stock.app.errorhandling;

import com.emezon.stock.domain.exceptions.brand.*;

public interface IBrandControllerAdvice<T> {

    Object handleBrandNameRequiredException(BrandNameRequiredException ex, T request);

    Object handleBrandNameAlreadyExistsException(BrandNameAlreadyExistsException ex, T request);

    Object handleBrandNameMaxLengthException(BrandNameMaxLengthException ex, T request);

    Object handleBrandDescriptionRequiredException(BrandDescriptionRequiredException ex, T request);

    Object handleBrandDescriptionMaxLengthException(BrandDescriptionMaxLengthException ex, T request);

    Object handleBrandNotFoundByIdException(BrandNotFoundByIdException ex, T request);

}
