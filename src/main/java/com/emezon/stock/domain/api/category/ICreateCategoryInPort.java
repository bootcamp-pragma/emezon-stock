package com.emezon.stock.domain.api.category;

import com.emezon.stock.domain.models.Category;

public interface ICreateCategoryInPort {

    Category createCategory(Category category);
    
}
