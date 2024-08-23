package com.emezon.stock.app.exceptions.category;

import com.emezon.stock.app.errorhandling.messages.CategoryErrorMessages;

public class CategoryDescriptionMinLengthException extends RuntimeException {
    public CategoryDescriptionMinLengthException(Integer minLength) {
        super(String.format(CategoryErrorMessages.CATEGORY_DESCRIPTION_MIN_LENGTH, minLength));
    }
}
