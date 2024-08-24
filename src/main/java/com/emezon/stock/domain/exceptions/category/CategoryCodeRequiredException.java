package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.domain.common.constants.CategoryErrorMessages;

public class CategoryCodeRequiredException extends RuntimeException {
    public CategoryCodeRequiredException() {
        super(CategoryErrorMessages.CATEGORY_CODE_REQUIRED);
    }
}
