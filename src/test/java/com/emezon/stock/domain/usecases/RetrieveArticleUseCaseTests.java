package com.emezon.stock.domain.usecases;

import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.models.Article;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.outbound.IArticleRepositoryOutPort;
import com.emezon.stock.domain.usecases.article.RetrieveArticleUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetrieveArticleUseCaseTests {

    @Mock
    private IArticleRepositoryOutPort articleRepositoryOutPort;

    @InjectMocks
    private RetrieveArticleUseCase retrieveArticleUseCase;

    private Article article;

    @BeforeEach
    public void setUp() {
        Category category = new Category("12345", "CAT1", "Electronics", "Electronic devices");
        Brand brand = new Brand("12354", "SAMSUNG", "Samsung Electronics");
        article = new Article("12378", "Galaxy S21", "Samsung Galaxy S21", 1000.0, 20, brand, List.of(category));
    }

    @Test
    void getArticleById_whenArticleExists_thenArticleIsReturned() {
        when(articleRepositoryOutPort.findById(article.getId())).thenReturn(Optional.of(article));

        Optional<Article> result = retrieveArticleUseCase.getArticleById(article.getId());

        assertTrue(result.isPresent());
        Article articleResult = result.get();
        assertEquals(article.getName(), articleResult.getName());

        verify(articleRepositoryOutPort, times(1)).findById(article.getId());
    }

    @Test
    void getArticleById_whenArticleDoesNotExists_thenArticleIsNotReturned() {
        when(articleRepositoryOutPort.findById(article.getId())).thenReturn(Optional.empty());

        Optional<Article> result = retrieveArticleUseCase.getArticleById(article.getId());

        assertTrue(result.isEmpty());

        verify(articleRepositoryOutPort, times(1)).findById(article.getId());
    }

    @Test
    void getAllArticles_whenParamsAreValid_thenAllArticlesAreReturned() {
        PaginatedResponse<Article> paginatedResponse = new PaginatedResponse<>();
        int page = 0;
        int size = 10;
        paginatedResponse.setPage(0);
        paginatedResponse.setSize(size);
        paginatedResponse.setItems(List.of(article));
        paginatedResponse.setTotalItems(1);
        paginatedResponse.setTotalPages(1);

        List<String> sorting = List.of("name,desc");
        when(articleRepositoryOutPort.findAll(page, size, sorting)).thenReturn(paginatedResponse);

        PaginatedResponse<Article> result = retrieveArticleUseCase.getAllArticles(page, size, sorting);
        assertNotNull(result);
        assertEquals(page, result.getPage());
        assertEquals(size, result.getSize());
        assertEquals(1, result.getTotalItems());
        assertEquals(1, result.getTotalPages());
        assertEquals(article.getId(), result.getItems().get(0).getId());

        verify(articleRepositoryOutPort, times(1)).findAll(page, size, sorting);
    }

}
