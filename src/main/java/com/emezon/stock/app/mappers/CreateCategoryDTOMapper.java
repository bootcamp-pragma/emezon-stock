package com.emezon.stock.app.mappers;

import com.emezon.stock.app.dtos.CreateCategoryDTO;
import com.emezon.stock.domain.models.Category;

import java.util.HashSet;

public class CreateCategoryDTOMapper {

    private CreateCategoryDTOMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Category toModel(CreateCategoryDTO createCategoryDTO) {
        Category category = new Category();
        category.setName(createCategoryDTO.getName());
        category.setDescription(createCategoryDTO.getDescription());
        category.setCode(createCategoryDTO.getCode());
        return category;
    }

    public static CreateCategoryDTO toDTO(Category category) {
        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO();
        createCategoryDTO.setName(category.getName());
        createCategoryDTO.setDescription(category.getDescription());
        createCategoryDTO.setCode(category.getCode());
        return createCategoryDTO;
    }

}
