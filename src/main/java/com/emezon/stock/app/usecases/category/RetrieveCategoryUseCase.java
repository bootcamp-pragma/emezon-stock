package com.emezon.stock.app.usecases.category;

import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.input.category.IRetrieveCategoryInPort;
import com.emezon.stock.domain.ports.output.ICategoryRepositoryOutPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class RetrieveCategoryUseCase implements IRetrieveCategoryInPort {

    private final ICategoryRepositoryOutPort categoryRepositoryOutPort;

    @Override
    public Optional<Category> getCategoryById(String id) {
        return categoryRepositoryOutPort.findById(id);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return categoryRepositoryOutPort.findByName(name);
    }

    @Override
    public Optional<Category> getCategoryByCode(String code) {
        return categoryRepositoryOutPort.findByCode(code);
    }

    @Override
    public Set<Category> getAllCategories() {
        return categoryRepositoryOutPort.findAll();
    }
}
