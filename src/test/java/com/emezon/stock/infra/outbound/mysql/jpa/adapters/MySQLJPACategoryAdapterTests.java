package com.emezon.stock.infra.outbound.mysql.jpa.adapters;

import com.emezon.stock.domain.models.Category;
import com.emezon.stock.infra.outbound.mysql.jpa.entities.CategoryEntity;
import com.emezon.stock.infra.outbound.mysql.jpa.mappers.CategoryEntityMapper;
import com.emezon.stock.infra.outbound.mysql.jpa.repositories.IMySQLJPACategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MySQLJPACategoryAdapterTests {

    @Mock
    private IMySQLJPACategoryRepository repository;

    @InjectMocks
    private MySQLJPACategoryAdapter adapter;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category("Electronics", "ELECT", "Electronics category");
    }

    @Test
    void save_whenCategoryIsSaved_thenShouldReturnCategory() {
        CategoryEntity categoryEntity = CategoryEntityMapper.toEntity(category);
        when(repository.save(any())).thenReturn(categoryEntity);

        Category savedCategory = adapter.save(category);

        assertNotNull(savedCategory);
        assertEquals(category.getName(), savedCategory.getName());

        verify(repository, times(1)).save(any());
    }

    @Test
    void findById_whenCategoryExists_thenShouldReturnCategory() {
        category.setId("123");
        CategoryEntity categoryEntity = CategoryEntityMapper.toEntity(category);
        when(repository.findById(category.getId())).thenReturn(Optional.of(categoryEntity));

        Optional<Category> foundCategory = adapter.findById(category.getId());
        assertTrue(foundCategory.isPresent());
        assertEquals(category.getName(), foundCategory.get().getName());

        verify(repository, times(1)).findById(category.getId());
    }

    @Test
    void findById_whenCategoryDoesNotExist_thenShouldReturnEmpty() {
        category.setId("123");
        when(repository.findById(category.getId())).thenReturn(Optional.empty());

        Optional<Category> foundCategory = adapter.findById(category.getId());
        assertTrue(foundCategory.isEmpty());

        verify(repository, times(1)).findById(category.getId());
    }

    @Test
    void findByName_whenCategoryExists_thenShouldReturnCategory() {
        CategoryEntity categoryEntity = CategoryEntityMapper.toEntity(category);
        when(repository.findByName(category.getName())).thenReturn(Optional.of(categoryEntity));

        Optional<Category> foundCategory = adapter.findByName(category.getName());
        assertTrue(foundCategory.isPresent());
        assertEquals(category.getName(), foundCategory.get().getName());

        verify(repository, times(1)).findByName(category.getName());
    }

    @Test
    void findByName_whenCategoryDoesNotExist_thenShouldReturnEmpty() {
        when(repository.findByName(category.getName())).thenReturn(Optional.empty());

        Optional<Category> foundCategory = adapter.findByName(category.getName());
        assertTrue(foundCategory.isEmpty());

        verify(repository, times(1)).findByName(category.getName());
    }

}
