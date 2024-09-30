package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.domain.constants.CategoryErrorMessages;

public class CategoryNameMaxLengthException extends RuntimeException {
    public CategoryNameMaxLengthException(Integer maxLength) {
        super(String.format(CategoryErrorMessages.CATEGORY_NAME_TOO_LONG, maxLength));
    }
}
