package com.emezon.stock.domain.ports.input.category;

import com.emezon.stock.domain.models.Category;

public interface ICreateCategoryInPort {

    Category createCategory(Category category);
    
}
