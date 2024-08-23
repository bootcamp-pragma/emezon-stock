package com.emezon.stock.app.exceptions.category;

import com.emezon.stock.app.errorhandling.messages.CategoryErrorMessages;

public class CategoryCodeMinLengthException extends RuntimeException {
    public CategoryCodeMinLengthException(Integer minLength) {
        super(String.format(CategoryErrorMessages.CATEGORY_CODE_MIN_LENGTH, minLength));
    }
}
