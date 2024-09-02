package com.emezon.stock.app.mappers;

import com.emezon.stock.app.dtos.category.CategoryDTO;
import com.emezon.stock.app.dtos.category.CreateCategoryDTO;
import com.emezon.stock.domain.models.Category;

import java.util.List;

public class CategoryDTOMapper {

    private CategoryDTOMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Category toModel(CreateCategoryDTO createCategoryDTO) {
        Category category = new Category();
        category.setName(createCategoryDTO.getName());
        category.setDescription(createCategoryDTO.getDescription());
        category.setCode(createCategoryDTO.getCode());
        return category;
    }

    public static Category toModel(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setCode(categoryDTO.getCode());
        return category;
    }

    public static CategoryDTO toDTO(Category category) {
        CategoryDTO createCategoryDTO = new CategoryDTO();
        createCategoryDTO.setId(category.getId());
        createCategoryDTO.setName(category.getName());
        createCategoryDTO.setDescription(category.getDescription());
        createCategoryDTO.setCode(category.getCode());
        return createCategoryDTO;
    }

}
