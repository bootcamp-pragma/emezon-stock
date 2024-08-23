package com.emezon.stock.app.exceptions.category;

import com.emezon.stock.app.errorhandling.messages.CategoryErrorMessages;

public class CategoryNameMaxLengthException extends RuntimeException {
    public CategoryNameMaxLengthException(Integer maxLength) {
        super(String.format(CategoryErrorMessages.CATEGORY_NAME_MAX_LENGTH, maxLength));
    }
}
