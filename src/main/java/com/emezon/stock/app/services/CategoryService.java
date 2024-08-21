package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.CategoryDTO;
import com.emezon.stock.app.mappers.CategoryDTOMapper;
import com.emezon.stock.app.usecases.category.CreateCategoryUseCase;
import com.emezon.stock.app.usecases.category.RetrieveCategoryUseCase;
import com.emezon.stock.domain.models.Category;
//import com.emezon.stock.domain.ports.input.category.ICreateCategoryInPort;
//import com.emezon.stock.domain.ports.input.category.IRetrieveCategoryInPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
//public class CategoryService implements ICreateCategoryInPort, IRetrieveCategoryInPort {
public class CategoryService {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final RetrieveCategoryUseCase retrieveCategoryUseCase;

    public Category createCategory(CategoryDTO category) {
        Optional<Category> categoryByName = retrieveCategoryUseCase.getCategoryByName(category.getName());
        if (categoryByName.isPresent()) {
            throw new IllegalArgumentException("Category already exists");
        }
        Optional<Category> categoryByCode = retrieveCategoryUseCase.getCategoryByCode(category.getCode());
        if (categoryByCode.isPresent()) {
            throw new IllegalArgumentException("Category code already exists");
        }
        Category categoryModel = CategoryDTOMapper.toModel(category);
        return createCategoryUseCase.createCategory(categoryModel);
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

    public Set<Category> getAllCategories() {
        return retrieveCategoryUseCase.getAllCategories();
    }

}
