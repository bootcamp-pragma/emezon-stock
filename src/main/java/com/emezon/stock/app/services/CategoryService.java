package com.emezon.stock.app.services;

import com.emezon.stock.app.usecases.category.CreateCategoryUseCase;
import com.emezon.stock.app.usecases.category.RetrieveCategoryUseCase;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.input.category.ICreateCategoryInPort;
import com.emezon.stock.domain.ports.input.category.IRetrieveCategoryInPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class CategoryService implements ICreateCategoryInPort, IRetrieveCategoryInPort {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final RetrieveCategoryUseCase retrieveCategoryUseCase;

    @Override
    public Category createCategory(Category category) {
        return createCategoryUseCase.createCategory(category);
    }

    @Override
    public Optional<Category> getCategoryById(String id) {
        return retrieveCategoryUseCase.getCategoryById(id);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return retrieveCategoryUseCase.getCategoryByName(name);
    }

    @Override
    public Optional<Category> getCategoryByCode(String code) {
        return retrieveCategoryUseCase.getCategoryByCode(code);
    }

    @Override
    public Set<Category> getAllCategories() {
        return retrieveCategoryUseCase.getAllCategories();
    }

}
