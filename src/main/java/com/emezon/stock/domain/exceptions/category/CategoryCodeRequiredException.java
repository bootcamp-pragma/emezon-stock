package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.app.errorhandling.messages.CategoryErrorMessages;

public class CategoryCodeRequiredException extends RuntimeException {
    public CategoryCodeRequiredException() {
        super(CategoryErrorMessages.CATEGORY_CODE_REQUIRED);
    }
}
