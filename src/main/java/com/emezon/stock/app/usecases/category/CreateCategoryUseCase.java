package com.emezon.stock.app.usecases.category;

import com.emezon.stock.app.exceptions.category.*;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.input.category.ICreateCategoryInPort;
import com.emezon.stock.domain.ports.input.category.IRetrieveCategoryInPort;
import com.emezon.stock.domain.ports.output.ICategoryRepositoryOutPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class CreateCategoryUseCase implements ICreateCategoryInPort, IRetrieveCategoryInPort {
    public static final Integer NAME_MAX_LENGTH = 50;
    public static final Integer DESCRIPTION_MAX_LENGTH = 90;
    public static final Integer DESCRIPTION_MIN_LENGTH = 5;

    private final ICategoryRepositoryOutPort categoryRepositoryOutPort;

    @Override
    public Category createCategory(Category category) {
        if (category.getName() == null) {
            throw new CategoryNameRequiredException();
        }
        if (category.getCode() == null) {
            throw new CategoryCodeRequiredException();
        }
        if (category.getDescription() == null) {
            throw new CategoryDescriptionRequiredException();
        }
        category.setName(category.getName().trim());
        category.setCode(category.getCode().trim());
        category.setDescription(category.getDescription().trim());
        if (category.getName().length() > NAME_MAX_LENGTH) {
            throw new CategoryNameMaxLengthException(NAME_MAX_LENGTH);
        }
        if (category.getDescription().length() > DESCRIPTION_MAX_LENGTH) {
            throw new CategoryDescriptionMaxLengthException(DESCRIPTION_MAX_LENGTH);
        }
        if (category.getDescription().length() < DESCRIPTION_MIN_LENGTH) {
            throw new CategoryDescriptionMinLengthException(DESCRIPTION_MIN_LENGTH);
        }
        Optional<Category> categoryByName = getCategoryByName(category.getName());
        if (categoryByName.isPresent()) {
            throw new CategoryNameAlreadyExistsException(category.getName());
        }
        Optional<Category> categoryByCode = getCategoryByCode(category.getCode());
        if (categoryByCode.isPresent()) {
            throw new CategoryCodeAlreadyExistsException(category.getCode());
        }
        return categoryRepositoryOutPort.save(category);
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
    public Set<Category> getAllCategories() {
        return categoryRepositoryOutPort.findAll();
    }
}
