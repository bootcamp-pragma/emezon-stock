package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.domain.common.constants.CategoryErrorMessages;

public class CategoryPageNumberInvalidException extends RuntimeException {
    public CategoryPageNumberInvalidException() {
        super(CategoryErrorMessages.CATEGORY_PAGE_NUMBER_INVALID);
    }
}
