package com.emezon.stock.domain.usecases.category;

import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.common.constants.CategoryConstraints;
import com.emezon.stock.domain.exceptions.category.*;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.input.category.ICreateCategoryInPort;
import com.emezon.stock.domain.ports.input.category.IRetrieveCategoryInPort;
import com.emezon.stock.domain.ports.output.ICategoryRepositoryOutPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CreateCategoryUseCase implements ICreateCategoryInPort, IRetrieveCategoryInPort {

    private final ICategoryRepositoryOutPort categoryRepositoryOutPort;

    @Override
    public Category createCategory(Category category) {
        Category processedCategory = processAndValidateCategory(category);
        Optional<Category> categoryByName = getCategoryByName(processedCategory.getName());
        if (categoryByName.isPresent()) {
            throw new CategoryNameAlreadyExistsException(processedCategory.getName());
        }
        Optional<Category> categoryByCode = getCategoryByCode(processedCategory.getCode());
        if (categoryByCode.isPresent()) {
            throw new CategoryCodeAlreadyExistsException(processedCategory.getCode());
        }
        return categoryRepositoryOutPort.save(processedCategory);
    }

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
    public PaginatedResponse<Category> getAllCategories(int page, int size) {
        return categoryRepositoryOutPort.findAll(page, size);
    }

    private Category processAndValidateCategory(Category category) {
        if (category.getName() == null) { throw new CategoryNameRequiredException(); }
        category.setName(category.getName().trim());
        if (category.getName().length() > CategoryConstraints.NAME_MAX_LENGTH) {
            throw new CategoryNameMaxLengthException(CategoryConstraints.NAME_MAX_LENGTH);
        }
        if (category.getCode() == null) { throw new CategoryCodeRequiredException(); }
        category.setCode(category.getCode().trim());
        if (category.getDescription() == null) { throw new CategoryDescriptionRequiredException(); }
        category.setDescription(category.getDescription().trim());
        if (category.getDescription().length() > CategoryConstraints.DESCRIPTION_MAX_LENGTH) {
            throw new CategoryDescriptionMaxLengthException(CategoryConstraints.DESCRIPTION_MAX_LENGTH);
        }
        return category;
    }

}
