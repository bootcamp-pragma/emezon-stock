package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.domain.common.constants.CategoryErrorMessages;

public class CategoryNameRequiredException extends RuntimeException {
    public CategoryNameRequiredException() {
        super(CategoryErrorMessages.CATEGORY_NAME_REQUIRED);
    }
}
