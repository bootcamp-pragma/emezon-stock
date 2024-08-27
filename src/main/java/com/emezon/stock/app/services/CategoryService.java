package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.CategoryDTO;
import com.emezon.stock.app.dtos.CreateCategoryDTO;
import com.emezon.stock.app.mappers.CreateCategoryDTOMapper;
import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.usecases.category.CreateCategoryUseCase;
import com.emezon.stock.domain.usecases.category.RetrieveCategoryUseCase;
import com.emezon.stock.domain.models.Category;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryService {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final RetrieveCategoryUseCase retrieveCategoryUseCase;

    public CategoryDTO createCategory(CreateCategoryDTO category) {
        Category categoryModel = CreateCategoryDTOMapper.toModel(category);
        Category createdCategory = createCategoryUseCase.createCategory(categoryModel);
        return CreateCategoryDTOMapper.toDTO(createdCategory);
    }

    public Optional<CategoryDTO> getCategoryById(String id) {
        Optional<Category> category = retrieveCategoryUseCase.getCategoryById(id);
        return category.map(CreateCategoryDTOMapper::toDTO);
    }

    public Optional<CategoryDTO> getCategoryByName(String name) {
        Optional<Category> category = retrieveCategoryUseCase.getCategoryByName(name);
        return category.map(CreateCategoryDTOMapper::toDTO);
    }

    public Optional<CategoryDTO> getCategoryByCode(String code) {
        Optional<Category> category = retrieveCategoryUseCase.getCategoryByCode(code);
        return category.map(CreateCategoryDTOMapper::toDTO);
    }

    public PaginatedResponse<CategoryDTO> getAllCategories(int page, int size, String sortDirection) {
        PaginatedResponse<Category> categories = retrieveCategoryUseCase.getAllCategories(page, size, sortDirection);
        List<CategoryDTO> categoryDTOS = CreateCategoryDTOMapper.toDTOList(categories.getItems());
        return new PaginatedResponse<>(
                categoryDTOS,
                categories.getPage(),
                categories.getSize(),
                categories.getTotalItems(),
                categories.getTotalPages()
        );
    }

}
