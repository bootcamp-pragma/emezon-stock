package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.domain.common.constants.CategoryErrorMessages;

public class CategoryDescriptionMinLengthException extends RuntimeException {
    public CategoryDescriptionMinLengthException(Integer minLength) {
        super(String.format(CategoryErrorMessages.CATEGORY_DESCRIPTION_MIN_LENGTH, minLength));
    }
}
