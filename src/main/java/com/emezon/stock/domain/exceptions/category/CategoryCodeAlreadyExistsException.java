package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.domain.common.constants.CategoryErrorMessages;

public class CategoryCodeAlreadyExistsException extends RuntimeException {

    public CategoryCodeAlreadyExistsException(String code) {
        super(String.format(CategoryErrorMessages.CATEGORY_CODE_ALREADY_EXISTS, code));
    }

}
