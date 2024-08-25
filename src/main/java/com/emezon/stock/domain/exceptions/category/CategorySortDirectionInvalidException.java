package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.domain.common.constants.CategoryErrorMessages;

public class CategorySortDirectionInvalidException extends RuntimeException {

    public CategorySortDirectionInvalidException() {
        super(CategoryErrorMessages.CATEGORY_SORT_DIRECTION_INVALID);
    }

}
