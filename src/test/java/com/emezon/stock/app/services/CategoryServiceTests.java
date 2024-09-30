package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.category.CategoryDTO;
import com.emezon.stock.app.dtos.category.CreateCategoryDTO;
import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.common.PaginatedResponseParams;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.usecases.category.CreateCategoryUseCase;
import com.emezon.stock.domain.usecases.category.RetrieveCategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTests {

    @Mock
    private CreateCategoryUseCase createCategoryUseCase;

    @Mock
    private RetrieveCategoryUseCase retrieveCategoryUseCase;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category("Electronics", "ELECT", "Devices and gadgets");
    }

    @Test
    void createCategory_whenCategoryPropertiesAreValid_thenCategoryIsCreated() {
        when(createCategoryUseCase.createCategory(any())).thenReturn(category);

        CreateCategoryDTO categoryDTO = new CreateCategoryDTO("  Electronics", "ELECT", "Devices and gadgets ");
        categoryDTO.init();
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);

        assertNotNull(createdCategory);
        assertEquals(category.getName(), createdCategory.getName());
        assertEquals(category.getCode(), createdCategory.getCode());
        assertEquals(category.getDescription(), createdCategory.getDescription());

        verify(createCategoryUseCase, times(1)).createCategory(any());
    }

    @Test
    void findCategoryById_whenCategoryExists_thenCategoryIsReturned() {
        when(retrieveCategoryUseCase.getCategoryById(any())).thenReturn(Optional.of(category));

        Optional<CategoryDTO> categoryById = categoryService.getCategoryById("1");

        assertTrue(categoryById.isPresent());
        assertEquals(category.getName(), categoryById.get().getName());

        verify(retrieveCategoryUseCase, times(1)).getCategoryById(any());
    }

    @Test
    void findCategoryById_whenCategoryDoesNotExist_thenEmptyOptionalIsReturned() {
        when(retrieveCategoryUseCase.getCategoryById(any())).thenReturn(Optional.empty());

        Optional<CategoryDTO> categoryById = categoryService.getCategoryById("1");

        assertTrue(categoryById.isEmpty());

        verify(retrieveCategoryUseCase, times(1)).getCategoryById(any());
    }

    @Test
    void findCategoryByName_whenCategoryExists_thenCategoryIsReturned() {
        when(retrieveCategoryUseCase.getCategoryByName(any())).thenReturn(Optional.of(category));

        Optional<CategoryDTO> categoryByName = categoryService.getCategoryByName("Electronics");

        assertTrue(categoryByName.isPresent());
        assertEquals(category.getName(), categoryByName.get().getName());

        verify(retrieveCategoryUseCase, times(1)).getCategoryByName(any());
    }

    @Test
    void findCategoryByName_whenCategoryDoesNotExist_thenEmptyOptionalIsReturned() {
        when(retrieveCategoryUseCase.getCategoryByName(any())).thenReturn(Optional.empty());

        Optional<CategoryDTO> categoryByName = categoryService.getCategoryByName("Electronics");

        assertTrue(categoryByName.isEmpty());

        verify(retrieveCategoryUseCase, times(1)).getCategoryByName(any());
    }

    @Test
    void findCategoryByCode_whenCategoryExists_thenCategoryIsReturned() {
        when(retrieveCategoryUseCase.getCategoryByCode(any())).thenReturn(Optional.of(category));

        Optional<CategoryDTO> categoryByCode = categoryService.getCategoryByCode("ELECT");

        assertTrue(categoryByCode.isPresent());
        assertEquals(category.getName(), categoryByCode.get().getName());

        verify(retrieveCategoryUseCase, times(1)).getCategoryByCode(any());
    }

    @Test
    void findCategoryByCode_whenCategoryDoesNotExist_thenEmptyOptionalIsReturned() {
        when(retrieveCategoryUseCase.getCategoryByCode(any())).thenReturn(Optional.empty());

        Optional<CategoryDTO> categoryByCode = categoryService.getCategoryByCode("ELECT");

        assertTrue(categoryByCode.isEmpty());

        verify(retrieveCategoryUseCase, times(1)).getCategoryByCode(any());
    }

    @Test
    void getAllCategories_whenCategoriesExist_thenPaginatedResponseIsReturned() {
        int page = 1, size = 1;
        PaginatedResponse<Category> categories = new PaginatedResponse<>(List.of(category), page, size, 1, 1);
        when(retrieveCategoryUseCase.getAllCategories(any())).thenReturn(categories);

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", String.valueOf(page));
        queryParams.add("size", String.valueOf(size));
        queryParams.add("sort", "name,asc");

        PaginatedResponse<CategoryDTO> allCategories = categoryService.getAllCategories(queryParams);

        assertNotNull(allCategories);
        assertEquals(1, allCategories.getItems().size());
        assertEquals(page, allCategories.getPage());
        assertEquals(size, allCategories.getSize());
        assertEquals(1, allCategories.getTotalItems());
        assertEquals(1, allCategories.getTotalPages());

        verify(retrieveCategoryUseCase, times(1)).getAllCategories(any());
    }

}
