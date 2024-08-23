package com.emezon.stock.app.exceptions.category;

import com.emezon.stock.app.errorhandling.messages.CategoryErrorMessages;

public class CategoryNameRequiredException extends RuntimeException {
    public CategoryNameRequiredException() {
        super(CategoryErrorMessages.CATEGORY_NAME_REQUIRED);
    }
}
