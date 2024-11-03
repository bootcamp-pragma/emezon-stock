package com.emezon.stock.domain.api.category;

import com.emezon.stock.domain.models.Category;

public interface IPersistCategoryInPort {

    Category createCategory(Category category);
    
}
