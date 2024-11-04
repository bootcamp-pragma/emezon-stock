package com.emezon.stock.app.handlers;

import com.emezon.stock.app.dtos.category.CategoryDTO;
import com.emezon.stock.app.dtos.category.CreateCategoryDTO;
import com.emezon.stock.domain.utils.PaginatedResponse;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

public interface ICategoryHandler {

    CategoryDTO createCategory(CreateCategoryDTO category);

    CategoryDTO getCategoryById(String id);

    CategoryDTO getCategoryByName(String name);

    PaginatedResponse<CategoryDTO> getAllCategories(MultiValueMap<String, String> queryParams);

}
