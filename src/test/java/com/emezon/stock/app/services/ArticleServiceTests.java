package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.app.dtos.article.ArticleListDTO;
import com.emezon.stock.app.dtos.article.CreateArticleDTO;
import com.emezon.stock.app.mappers.ArticleDTOMapper;
import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.models.Article;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.usecases.article.CreateArticleUseCase;
import com.emezon.stock.domain.usecases.article.RetrieveArticleUseCase;
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
class ArticleServiceTests {

    @Mock
    private CreateArticleUseCase createArticleUseCase;

    @Mock
    private RetrieveArticleUseCase retrieveArticleUseCase;

    @InjectMocks
    private ArticleService articleService;

    @Test
    void createArticle_whenArticlePropertiesAreValid_thenArticleIsCreated() {
        Brand brand = new Brand("1123", "Samsung", "Samsung Electronics Co., Ltd.");
        Category category = new Category("1234", "Smartphones category", "Smartphones", "Smartphones category description");
        CreateArticleDTO articleDTO = new CreateArticleDTO();
        articleDTO.setName("Samsung Galaxy S21");
        articleDTO.setDescription("Samsung Galaxy S21 description");
        articleDTO.setPrice(999.99);
        articleDTO.setStock(100);
        articleDTO.setBrandId(brand.getId());
        articleDTO.setCategoryIds(List.of(category.getId()));
        Article article = new Article("1234", articleDTO.getName(), articleDTO.getDescription(),
                articleDTO.getPrice(), articleDTO.getStock(), brand, List.of(category));
        when(createArticleUseCase.createArticle(any())).thenReturn(article);

        ArticleDTO createdArticle = articleService.createArticle(articleDTO);

        ArticleDTO expectedArticle = ArticleDTOMapper.toDTO(article);

        assertNotNull(createdArticle);
        assertEquals(expectedArticle.getName(), createdArticle.getName());
        assertEquals(expectedArticle.getDescription(), createdArticle.getDescription());
        assertEquals(expectedArticle.getPrice(), createdArticle.getPrice());
        assertEquals(expectedArticle.getStock(), createdArticle.getStock());
        assertEquals(expectedArticle.getBrand().getId(), createdArticle.getBrand().getId());
        assertEquals(expectedArticle.getCategories().size(), createdArticle.getCategories().size());
        assertEquals(expectedArticle.getCategories().get(0).getId(), createdArticle.getCategories().get(0).getId());

        verify(createArticleUseCase, times(1)).createArticle(any());
    }

    @Test
    void getArticleById_whenArticleExists_thenArticleIsReturned() {
        Brand brand = new Brand("1123", "Samsung", "Samsung Electronics Co., Ltd.");
        Category category = new Category("1234", "Smartphones category", "Smartphones", "Smartphones category description");
        Article article = new Article("1234", "Samsung Galaxy S21", "Samsung Galaxy S21 description",
                999.99, 100, brand, List.of(category));
        when(retrieveArticleUseCase.getArticleById(article.getId())).thenReturn(Optional.of(article));

        ArticleDTO foundArticle = articleService.getArticleById(article.getId()).orElse(null);

        ArticleDTO expectedArticle = ArticleDTOMapper.toDTO(article);

        assertNotNull(foundArticle);
        assertEquals(expectedArticle.getName(), foundArticle.getName());
        assertEquals(expectedArticle.getDescription(), foundArticle.getDescription());
        assertEquals(expectedArticle.getPrice(), foundArticle.getPrice());
        assertEquals(expectedArticle.getStock(), foundArticle.getStock());
        assertEquals(expectedArticle.getBrand().getId(), foundArticle.getBrand().getId());
        assertEquals(expectedArticle.getCategories().size(), foundArticle.getCategories().size());
        assertEquals(expectedArticle.getCategories().get(0).getId(), foundArticle.getCategories().get(0).getId());

        verify(retrieveArticleUseCase, times(1)).getArticleById(article.getId());
    }

    @Test
    void getArticleById_whenArticleDoesNotExist_thenArticleIsNotReturned() {
        when(retrieveArticleUseCase.getArticleById("123")).thenReturn(Optional.empty());

        Optional<ArticleDTO> foundArticle = articleService.getArticleById("123");
        assertTrue(foundArticle.isEmpty());

        verify(retrieveArticleUseCase, times(1)).getArticleById("123");
    }

    @Test
    void getAllArticles_whenArticlesExist_thenArticlesSerAreReturned() {
        Brand brand = new Brand("1123", "Samsung", "Samsung Electronics Co., Ltd.");
        Category category = new Category("1234", "Smartphones category", "Smartphones", "Smartphones category description");
        Article article = new Article("1234", "Samsung Galaxy S21", "Samsung Galaxy S21 description",
                999.99, 100, brand, List.of(category));
        int page = 0, size = 1;
        PaginatedResponse<Article> pr = new PaginatedResponse<>(List.of(article), page, size, 1, 1);
        when(retrieveArticleUseCase.getAllArticles(any())).thenReturn(pr);

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", String.valueOf(page));
        queryParams.add("size", String.valueOf(size));
        PaginatedResponse<ArticleListDTO> allArticles = articleService.getAllArticles(queryParams);

        assertNotNull(allArticles);
        assertEquals(pr.getItems().size(), allArticles.getItems().size());
        assertEquals(pr.getPage(), allArticles.getPage());
        assertEquals(pr.getSize(), allArticles.getSize());
        assertEquals(pr.getTotalItems(), allArticles.getTotalItems());
        assertEquals(pr.getTotalPages(), allArticles.getTotalPages());

        verify(retrieveArticleUseCase, times(1)).getAllArticles(any());
    }

}
