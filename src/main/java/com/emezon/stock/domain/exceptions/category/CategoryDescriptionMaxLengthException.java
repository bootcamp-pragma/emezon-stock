package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.domain.constants.CategoryErrorMessages;

public class CategoryDescriptionMaxLengthException extends RuntimeException {
    public CategoryDescriptionMaxLengthException(Integer maxLength) {
        super(String.format(CategoryErrorMessages.CATEGORY_DESCRIPTION_TOO_LONG, maxLength));
    }
}
