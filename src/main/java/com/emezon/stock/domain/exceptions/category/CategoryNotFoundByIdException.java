package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.domain.constants.CategoryErrorMessages;

public class CategoryNotFoundByIdException extends RuntimeException {
    public CategoryNotFoundByIdException(String id) {
        super(String.format(CategoryErrorMessages.CATEGORY_NOT_FOUND_BY_ID, id));
    }
}
