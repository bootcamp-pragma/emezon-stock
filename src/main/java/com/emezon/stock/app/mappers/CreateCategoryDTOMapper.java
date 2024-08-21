package com.emezon.stock.app.mappers;

import com.emezon.stock.app.dtos.CreateCategoryDTO;
import com.emezon.stock.domain.models.Category;

public class CreateCategoryDTOMapper {

    public static Category toModel(CreateCategoryDTO createCategoryDTO) {
        return new Category(
                null,
                createCategoryDTO.getName(),
                createCategoryDTO.getDescription(),
                createCategoryDTO.getCode());
    }

    public static CreateCategoryDTO toDTO(Category category) {
        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO();
        createCategoryDTO.setName(category.getName());
        createCategoryDTO.setDescription(category.getDescription());
        createCategoryDTO.setCode(category.getCode());
        return createCategoryDTO;
    }

}
