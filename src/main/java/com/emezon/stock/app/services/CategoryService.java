package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.category.CategoryDTO;
import com.emezon.stock.app.dtos.category.CreateCategoryDTO;
import com.emezon.stock.app.handlers.ICategoryHandler;
import com.emezon.stock.app.mappers.CategoryDTOMapper;
import com.emezon.stock.domain.api.category.IPersistCategoryInPort;
import com.emezon.stock.domain.api.category.IRetrieveCategoryInPort;
import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import com.emezon.stock.domain.utils.PaginatedResponseUtils;
import com.emezon.stock.domain.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryService implements ICategoryHandler {

    private final IPersistCategoryInPort persistCategoryInPort;
    private final IRetrieveCategoryInPort retrieveCategoryInPort;

    @Override
    public CategoryDTO createCategory(CreateCategoryDTO category) {
        Category categoryModel = CategoryDTOMapper.toModel(category);
        Category createdCategory = persistCategoryInPort.createCategory(categoryModel);
        return CategoryDTOMapper.toDTO(createdCategory);
    }

    @Override
    public CategoryDTO getCategoryById(String id) {
        Optional<Category> category = retrieveCategoryInPort.getCategoryById(id);
        return category.map(CategoryDTOMapper::toDTO).orElse(null);
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Optional<Category> category = retrieveCategoryInPort.getCategoryByName(name);
        return category.map(CategoryDTOMapper::toDTO).orElse(null);
    }

    @Override
    public PaginatedResponse<CategoryDTO> getAllCategories(MultiValueMap<String, String> queryParams) {
        PaginatedResponseParams params = PaginatedResponseUtils.getFromMap(queryParams);
        PaginatedResponse<Category> categories = retrieveCategoryInPort.getAllCategories(params);
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
