package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.category.CategoryDTO;
import com.emezon.stock.app.dtos.category.CreateCategoryDTO;
import com.emezon.stock.app.mappers.CategoryDTOMapper;
import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.common.PaginatedResponseParams;
import com.emezon.stock.domain.common.PaginatedResponseUtils;
import com.emezon.stock.domain.usecases.category.CreateCategoryUseCase;
import com.emezon.stock.domain.usecases.category.RetrieveCategoryUseCase;
import com.emezon.stock.domain.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryService {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final RetrieveCategoryUseCase retrieveCategoryUseCase;

    public CategoryDTO createCategory(CreateCategoryDTO category) {
        Category categoryModel = CategoryDTOMapper.toModel(category);
        Category createdCategory = createCategoryUseCase.createCategory(categoryModel);
        return CategoryDTOMapper.toDTO(createdCategory);
    }

    public Optional<CategoryDTO> getCategoryById(String id) {
        Optional<Category> category = retrieveCategoryUseCase.getCategoryById(id);
        return category.map(CategoryDTOMapper::toDTO);
    }

    public Optional<CategoryDTO> getCategoryByName(String name) {
        Optional<Category> category = retrieveCategoryUseCase.getCategoryByName(name);
        return category.map(CategoryDTOMapper::toDTO);
    }

    public Optional<CategoryDTO> getCategoryByCode(String code) {
        Optional<Category> category = retrieveCategoryUseCase.getCategoryByCode(code);
        return category.map(CategoryDTOMapper::toDTO);
    }

    public PaginatedResponse<CategoryDTO> getAllCategories(MultiValueMap<String, String> queryParams) {
        PaginatedResponseParams params = PaginatedResponseUtils.getFromMultiValueMap(queryParams);
        PaginatedResponse<Category> categories = retrieveCategoryUseCase.getAllCategories(params);
        List<CategoryDTO> categoryDTOS = categories.getItems().stream().map(CategoryDTOMapper::toDTO).toList();
        return new PaginatedResponse<>(
                categoryDTOS,
                categories.getPage(),
                categories.getSize(),
                categories.getTotalItems(),
                categories.getTotalPages()
        );
    }

}
