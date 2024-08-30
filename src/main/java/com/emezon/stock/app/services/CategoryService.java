package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.category.CategoryDTO;
import com.emezon.stock.app.dtos.category.CreateCategoryDTO;
import com.emezon.stock.app.mappers.CreateCategoryDTOMapper;
import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.constants.PaginatedResponseConstraints;
import com.emezon.stock.domain.usecases.category.CreateCategoryUseCase;
import com.emezon.stock.domain.usecases.category.RetrieveCategoryUseCase;
import com.emezon.stock.domain.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Objects;
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

    public PaginatedResponse<CategoryDTO> getAllCategories(MultiValueMap<String, String> queryParams) {
        int page = queryParams.containsKey("page") ?
                Integer.parseInt(Objects.requireNonNull(queryParams.getFirst("page"))) :
                PaginatedResponseConstraints.DEFAULT_PAGE_NUMBER;
        int size = queryParams.containsKey("size") ?
                Integer.parseInt(Objects.requireNonNull(queryParams.getFirst("size"))) :
                PaginatedResponseConstraints.DEFAULT_PAGE_SIZE;
        List<String> sort = queryParams.containsKey("sort") ?
                queryParams.get("sort") : List.of();
        PaginatedResponse<Category> categories = retrieveCategoryUseCase.getAllCategories(page, size, sort);
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
