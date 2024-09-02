package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.domain.constants.CategoryErrorMessages;

public class CategoryNameAlreadyExistsException extends RuntimeException {

    public CategoryNameAlreadyExistsException(String name) {
        super(String.format(CategoryErrorMessages.CATEGORY_ALREADY_EXISTS, name));
    }

}
