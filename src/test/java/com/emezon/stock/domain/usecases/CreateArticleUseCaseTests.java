package com.emezon.stock.domain.usecases;

import com.emezon.stock.domain.exceptions.article.ArticleCategoriesNumberInvalidException;
import com.emezon.stock.domain.exceptions.article.ArticleDuplicateCategoriesException;
import com.emezon.stock.domain.exceptions.article.ArticlePriceMinValueException;
import com.emezon.stock.domain.exceptions.article.ArticleStockMinValueException;
import com.emezon.stock.domain.exceptions.brand.BrandNotFoundByIdException;
import com.emezon.stock.domain.exceptions.category.CategoryNotFoundByIdException;
import com.emezon.stock.domain.models.Article;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.outbound.IArticleRepositoryOutPort;
import com.emezon.stock.domain.usecases.article.CreateArticleUseCase;
import com.emezon.stock.domain.usecases.brand.RetrieveBrandUseCase;
import com.emezon.stock.domain.usecases.category.RetrieveCategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateArticleUseCaseTests {

    @Mock
    private IArticleRepositoryOutPort articleRepositoryOutPort;

    @Mock
    private RetrieveBrandUseCase retrieveBrandUseCase;

    @Mock
    private RetrieveCategoryUseCase retrieveCategoryUseCase;

    @InjectMocks
    private CreateArticleUseCase createArticleUseCase;

    private Category category;
    private Brand brand;
    private Article article;

    @BeforeEach
    public void setUp() {
        category = new Category("76890", "NAME", "ELECTRONICS", "Electronic devices");
        brand = new Brand("456", "SAMSUNG", "Samsung Electronics");
        article = new Article("789", "SAMSUNG-123", "Samsung Galaxy S21", 1000.0, 10, brand, List.of(category));
    }

    @Test
    void createArticle_whenArticlePriceIsLessThanMinValue_thenArticlePriceMinValueExceptionIsThrown() {
        article.setPrice(-1.0);

        assertThrows(ArticlePriceMinValueException.class, () -> createArticleUseCase.createArticle(article));

        verify(articleRepositoryOutPort, never()).save(any());
    }

    @Test
    void createArticle_whenArticleStockIsLessThanMinValue_thenArticleStockMinValueExceptionIsThrown() {
        article.setStock(-1);

        assertThrows(ArticleStockMinValueException.class, () -> createArticleUseCase.createArticle(article));

        verify(articleRepositoryOutPort, never()).save(any());
    }

    @Test
    void createArticle_whenNumberOfCategoriesIsGreaterThanMaxValue_thenArticleCategoriesNumberInvalidExceptionIsThrown() {
        Category category1 = new Category("1234", "cod1", "Electronic devices");
        Category category2 = new Category("12345", "cod2", "Electronic devices");
        Category category3 = new Category("123456", "cod3", "Electronic devices");
        Category category4 = new Category("1234567", "cod4", "Electronic devices");

        article.setCategories(List.of(category1, category2, category3, category4));

        assertThrows(ArticleCategoriesNumberInvalidException.class, () -> createArticleUseCase.createArticle(article));

        verify(articleRepositoryOutPort, never()).save(any());
    }

    @Test
    void createArticle_whenNumberOfCategoriesIsLessThanMinValue_thenArticleCategoriesNumberInvalidExceptionIsThrown() {
        article.setCategories(List.of());

        assertThrows(ArticleCategoriesNumberInvalidException.class, () -> createArticleUseCase.createArticle(article));

        verify(articleRepositoryOutPort, never()).save(any());
    }

    @Test
    void createArticle_whenThereAreDuplicateCategories_thenArticleDuplicateCategoriesExceptionIsThrown() {
        article.setCategories(List.of(category, category));

        assertThrows(ArticleDuplicateCategoriesException.class, () -> createArticleUseCase.createArticle(article));

        verify(articleRepositoryOutPort, never()).save(any());
    }

    @Test
    void createArticle_whenBrandDoesNotExist_thenBrandNotFoundByIdExceptionIsThrown() {
        when(retrieveBrandUseCase.getBrandById(brand.getId())).thenReturn(Optional.empty());

        assertThrows(BrandNotFoundByIdException.class, () -> createArticleUseCase.createArticle(article));

        verify(articleRepositoryOutPort, never()).save(any());
    }

    @Test
    void createArticle_whenCategoryDoesNotExist_thenCategoryNotFoundByIdExceptionIsThrown() {
        when(retrieveBrandUseCase.getBrandById(brand.getId())).thenReturn(Optional.of(brand));
        when(retrieveCategoryUseCase.getCategoryById(category.getId())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundByIdException.class, () -> createArticleUseCase.createArticle(article));

        verify(articleRepositoryOutPort, never()).save(any());
    }

    @Test
    void createArticle_whenArticleIsValid_thenArticleIsCreated() {
        when(retrieveBrandUseCase.getBrandById(brand.getId())).thenReturn(Optional.of(brand));
        when(retrieveCategoryUseCase.getCategoryById(category.getId())).thenReturn(Optional.of(category));
        when(articleRepositoryOutPort.save(article)).thenReturn(article);

        Article createdArticle = createArticleUseCase.createArticle(article);
        assertNotNull(createdArticle);
        assertEquals(article.getName(), createdArticle.getName());
        assertEquals(article.getBrand().getName(), createdArticle.getBrand().getName());
        assertEquals(article.getCategories().size(), createdArticle.getCategories().size());

        verify(articleRepositoryOutPort, times(1)).save(article);
    }

}
