package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.app.errorhandling.messages.CategoryErrorMessages;

public class CategoryDescriptionRequiredException extends RuntimeException {
    public CategoryDescriptionRequiredException() {
        super(CategoryErrorMessages.CATEGORY_DESCRIPTION_REQUIRED);
    }
}
