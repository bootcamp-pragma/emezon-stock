package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.domain.common.constants.CategoryErrorMessages;

public class CategoryDescriptionMaxLengthException extends RuntimeException {
    public CategoryDescriptionMaxLengthException(Integer maxLength) {
        super(String.format(CategoryErrorMessages.CATEGORY_DESCRIPTION_MAX_LENGTH, maxLength));
    }
}
