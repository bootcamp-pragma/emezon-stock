package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.domain.common.constants.CategoryErrorMessages;

public class CategoryPageSizeInvalidException extends RuntimeException {
    public CategoryPageSizeInvalidException() {
        super(CategoryErrorMessages.CATEGORY_PAGE_SIZE_INVALID);
    }
}
