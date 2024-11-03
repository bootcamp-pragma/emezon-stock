package com.emezon.stock.app.mappers;

import com.emezon.stock.app.dtos.category.CategoryDTO;
import com.emezon.stock.app.dtos.category.CategoryIdNameDTO;
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
        return category;
    }

    public static Category toModel(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }

    public static CategoryDTO toDTO(Category category) {
        CategoryDTO createCategoryDTO = new CategoryDTO();
        createCategoryDTO.setId(category.getId());
        createCategoryDTO.setName(category.getName());
        createCategoryDTO.setDescription(category.getDescription());
        return createCategoryDTO;
    }

    public static CategoryIdNameDTO toIdNameDTO(Category category) {
        CategoryIdNameDTO categoryIdNameDTO = new CategoryIdNameDTO();
        categoryIdNameDTO.setId(category.getId());
        categoryIdNameDTO.setName(category.getName());
        return categoryIdNameDTO;
    }

}
