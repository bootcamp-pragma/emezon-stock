package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.domain.common.constants.CategoryErrorMessages;

public class CategoryCodeMinLengthException extends RuntimeException {
    public CategoryCodeMinLengthException(Integer minLength) {
        super(String.format(CategoryErrorMessages.CATEGORY_CODE_MIN_LENGTH, minLength));
    }
}
