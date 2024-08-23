package com.emezon.stock.app.exceptions.category;

import com.emezon.stock.app.errorhandling.messages.CategoryErrorMessages;

public class CategoryCodeAlreadyExistsException extends RuntimeException {

    public CategoryCodeAlreadyExistsException(String code) {
        super(String.format(CategoryErrorMessages.CATEGORY_CODE_ALREADY_EXISTS, code));
    }

}
