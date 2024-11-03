package com.emezon.stock.app.handlers;

import com.emezon.stock.app.dtos.category.CategoryDTO;
import com.emezon.stock.app.dtos.category.CreateCategoryDTO;
import com.emezon.stock.domain.utils.PaginatedResponse;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

public interface ICategoryHandler {

    public CategoryDTO createCategory(CreateCategoryDTO category);

    public Optional<CategoryDTO> getCategoryById(String id);

    public Optional<CategoryDTO> getCategoryByName(String name);

    public PaginatedResponse<CategoryDTO> getAllCategories(MultiValueMap<String, String> queryParams);

}
