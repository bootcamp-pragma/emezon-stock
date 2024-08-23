package com.emezon.stock.app.services;

import com.emezon.stock.app.exceptions.category.*;
import com.emezon.stock.domain.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    void shouldThrowException_whenNameIsNull() {
        Category category = new Category();
        category.setDescription("Electronic devices");
        category.setCode("ELEC");

        try {
            categoryService.createCategory(category);
            fail("Should have thrown an exception");
        } catch (CategoryNameRequiredException e) {
            // Success
        }
    }

    @Test
    void shouldThrowException_whenCodeIsNull() {
        Category category = new Category();
        category.setName("Electronics");
        category.setDescription("Electronic devices");

        try {
            categoryService.createCategory(category);
            fail("Should have thrown an exception");
        } catch (CategoryCodeRequiredException e) {
            // Success
        }
    }

    @Test
    void shouldThrowException_whenDescriptionIsNull() {
        Category category = new Category();
        category.setName("Electronics");
        category.setCode("ELEC");

        try {
            categoryService.createCategory(category);
            fail("Should have thrown an exception");
        } catch (CategoryDescriptionRequiredException e) {
            // Success
        }
    }

    @Test
    void shouldFindCategoryById_whenCategoryExists() {
        Category category = new Category();
        category.setName("shouldFindCategoryById");
        category.setDescription("Electronic devices");
        category.setCode("shouldFindCategoryById");
        Category createdCategory = categoryService.createCategory(category);
        Optional<Category> categoryById = categoryService.getCategoryById(createdCategory.getId());
        assertTrue(categoryById.isPresent());
        assertEquals(createdCategory.getId(), categoryById.get().getId());
    }

    @Test
    void shouldFindCategoryByCode_whenCategoryExists() {
        Category category = new Category();
        category.setName("shouldFindCategoryByCode");
        category.setDescription("Electronic devices");
        category.setCode("shouldFindCategoryByCode");

        categoryService.createCategory(category);

        Optional<Category> categoryByCode = categoryService.getCategoryByCode(category.getCode());
        assertTrue(categoryByCode.isPresent());
        assertEquals(category.getCode(), categoryByCode.get().getCode());
    }

    @Test
    void shouldFindCategoryByName_whenCategoryExists() {
        Category category = new Category();
        category.setName("shouldFindCategoryByName");
        category.setDescription("Electronic devices");
        category.setCode("shouldFindCategoryByName");

        categoryService.createCategory(category);

        Optional<Category> categoryByName = categoryService.getCategoryByName(category.getName());
        assertTrue(categoryByName.isPresent());
        assertEquals(category.getName(), categoryByName.get().getName());
    }

    @Test
    void shouldCreateCategory_whenValidProperties() {
        Category category = new Category();
        category.setName("Electronics");
        category.setDescription("Electronic devices");
        category.setCode("ELEC");

        Category createdCategory = categoryService.createCategory(category);
        assertNotNull(createdCategory);
        assertNotNull(createdCategory.getId());
        assertEquals(category.getName(), createdCategory.getName());
        assertEquals(category.getDescription(), createdCategory.getDescription());
        assertEquals(category.getCode(), createdCategory.getCode());

    }

    @Test
    void shouldThrowException_whenCategoryNameAlreadyExists() {
        Category category = new Category();
        category.setName("Electronics20");
        category.setDescription("Electronic devices");
        category.setCode("ELEC20");

        categoryService.createCategory(category);

        category.setCode("ELEC21");

        try {
            categoryService.createCategory(category);
            fail("Should have thrown an exception");
        } catch (CategoryNameAlreadyExistsException e) {
            // Success
        }
    }

    @Test
    void shouldThrowException_whenCategoryCodeAlreadyExists() {
        Category category = new Category();
        category.setName("Electronics33");
        category.setDescription("Electronic devices");
        category.setCode("ELEC3");

        categoryService.createCategory(category);

        category.setName("Electronics34");

        try {
            categoryService.createCategory(category);
            fail("Should have thrown an exception");
        } catch (CategoryCodeAlreadyExistsException e) {
            // Success
        }
    }

    @Test
    void shouldCreateCategory_whenCategoryNameLengthExceedsMaxLength() {
        Category category = new Category();
        category.setName("ElectronicsElectronicsElectronicsElectronicsElectronicsElectronicsElectronicsElectronicsElectronicsElectronics");
        category.setDescription("Electronic devices");
        category.setCode("ELEC");

        try {
            categoryService.createCategory(category);
            fail("Should have thrown an exception");
        } catch (CategoryNameMaxLengthException e) {
            // Success
        }
    }

    @Test
    void shouldCreateCategory_whenCategoryDescriptionLengthExceedsMaxLength() {
        Category category = new Category();
        category.setName("Electronics");
        category.setDescription("Electronic devicesElectronic devicesElectronic devicesElectronic devicesElectronic devicesElectronic devicesElectronic devicesElectronic devicesElectronic devicesElectronic devices");
        category.setCode("ELEC");

        try {
            categoryService.createCategory(category);
            fail("Should have thrown an exception");
        } catch (CategoryDescriptionMaxLengthException e) {
            // Success
        }
    }

    @Test
    void shouldCreateCategory_whenCategoryDescriptionLengthIsLessThanMinLength() {
        Category category = new Category();
        category.setName("Electronics");
        category.setDescription("         Elec       ");
        category.setCode("ELEC");

        try {
            categoryService.createCategory(category);
            fail("Should have thrown an exception");
        } catch (CategoryDescriptionMinLengthException e) {
            // Success
        }
    }

}
