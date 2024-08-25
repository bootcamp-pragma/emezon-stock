package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.CategoryDTO;
import com.emezon.stock.app.dtos.CreateCategoryDTO;
import com.emezon.stock.app.mappers.CreateCategoryDTOMapper;
import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.usecases.category.CreateCategoryUseCase;
import com.emezon.stock.domain.usecases.category.RetrieveCategoryUseCase;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.input.category.ICreateCategoryInPort;
import com.emezon.stock.domain.ports.input.category.IRetrieveCategoryInPort;
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

    public Optional<Category> getCategoryById(String id) {
        return retrieveCategoryUseCase.getCategoryById(id);
    }

    public Optional<Category> getCategoryByName(String name) {
        return retrieveCategoryUseCase.getCategoryByName(name);
    }

    public Optional<Category> getCategoryByCode(String code) {
        return retrieveCategoryUseCase.getCategoryByCode(code);
    }

    public PaginatedResponse<CategoryDTO> getAllCategories(int page, int size, String sortDirection) {
        PaginatedResponse<Category> categories = retrieveCategoryUseCase.getAllCategories(page, size, sortDirection);
        List<CategoryDTO> categoryDTOS = CreateCategoryDTOMapper.toDTOList(categories.getElements());
        return new PaginatedResponse<>(
                categoryDTOS,
                categories.getPage(),
                categories.getSize(),
                categories.getTotalElements(),
                categories.getTotalPages()
        );
    }

}
