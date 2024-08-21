package com.emezon.stock.app.mappers;

import com.emezon.stock.app.dtos.CategoryDTO;
import com.emezon.stock.domain.models.Category;

public class CategoryDTOMapper {

    public static CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setCode(category.getCode());
        return categoryDTO;
    }

    public static Category toModel(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getName(), categoryDTO.getDescription(), categoryDTO.getCode());
    }

}
