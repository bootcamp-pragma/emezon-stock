package com.emezon.stock.app.exceptions.category;

import com.emezon.stock.app.errorhandling.messages.CategoryErrorMessages;

public class CategoryNameAlreadyExistsException extends RuntimeException {

    public CategoryNameAlreadyExistsException(String name) {
        super(String.format(CategoryErrorMessages.CATEGORY_ALREADY_EXISTS, name));
    }

}
