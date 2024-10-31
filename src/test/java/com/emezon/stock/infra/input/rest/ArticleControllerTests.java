package com.emezon.stock.infra.input.rest;

import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.app.dtos.article.CreateArticleDTO;
import com.emezon.stock.app.dtos.brand.BrandDTO;
import com.emezon.stock.app.dtos.category.CategoryDTO;
import com.emezon.stock.app.services.ArticleService;
import com.emezon.stock.domain.constants.ArticleErrorMessages;
import com.emezon.stock.domain.constants.PaginatedResponseErrorMessages;
import com.emezon.stock.infra.constants.RestApiConstants;
import com.emezon.stock.infra.input.rest.controllers.ArticleController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleController.class)
class ArticleControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    private CreateArticleDTO createArticleDTO;
    private ArticleDTO articleDTO;
    private BrandDTO brandDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    static String genRanUUID() {
        return UUID.randomUUID().toString();
    }

    @Test
    void createArticle_whenArticlePropertiesAreValid_shouldReturnCreated() throws Exception {
        brandDTO = new BrandDTO(genRanUUID(), "Nike", "Just do it");
        List<CategoryDTO> categories = List.of(
                new CategoryDTO(genRanUUID(), "Electronics", "Electronic devices", "C123"),
                new CategoryDTO(genRanUUID(), "Smartphones", "Smartphones", "C456")
        );
        articleDTO = new ArticleDTO();
        articleDTO.setName("Smartphone");
        articleDTO.setDescription("A smartphone");
        articleDTO.setPrice(1000);
        articleDTO.setStock(10);
        articleDTO.setBrand(brandDTO);
        articleDTO.setCategories(categories);

        when(articleService.createArticle(any())).thenReturn(articleDTO);

        createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setName(articleDTO.getName());
        createArticleDTO.setDescription(articleDTO.getDescription());
        createArticleDTO.setPrice(articleDTO.getPrice());
        createArticleDTO.setStock(articleDTO.getStock());
        createArticleDTO.setBrandId(brandDTO.getId());
        createArticleDTO.setCategoryIds(categories.stream().map(CategoryDTO::getId).toList());

        mockMvc.perform(post(RestApiConstants.API_ARTICLE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createArticleDTO)))
                .andExpect(status().isCreated())
                        .andExpect(result -> {
                            ArticleDTO response = objectMapper.readValue(result.getResponse().getContentAsString(), ArticleDTO.class);
                            assert response.getName().equals(articleDTO.getName());
                            assert response.getDescription().equals(articleDTO.getDescription());
                            assertEquals(response.getPrice(), articleDTO.getPrice());
                            assertEquals(response.getStock(), articleDTO.getStock());
                            assertEquals(response.getBrand().getId(), articleDTO.getBrand().getId());
                            assertEquals(response.getCategories().size(), articleDTO.getCategories().size());
                        });

        verify(articleService, times(1)).createArticle(any());
    }

    @Test
    void createArticle_whenArticleNameIsBlank_shouldReturnBadRequest() throws Exception {
        createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setName("");
        createArticleDTO.setDescription("A smartphone");
        createArticleDTO.setPrice(1000);
        createArticleDTO.setStock(10);
        createArticleDTO.setBrandId(genRanUUID());
        createArticleDTO.setCategoryIds(List.of(genRanUUID()));

        mockMvc.perform(post(RestApiConstants.API_ARTICLE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createArticleDTO)))
                .andExpect(status().isBadRequest())
                        .andExpect(result -> {
                            String response = result.getResponse().getContentAsString();
                            assert response.contains(ArticleErrorMessages.ARTICLE_NAME_REQUIRED);
                        });

        verify(articleService, never()).createArticle(any());
    }

    @Test
    void createArticle_whenArticleDescriptionIsBlank_shouldReturnBadRequest() throws Exception {
        createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setName("Smartphone");
        createArticleDTO.setDescription("");
        createArticleDTO.setPrice(1000);
        createArticleDTO.setStock(10);
        createArticleDTO.setBrandId(genRanUUID());
        createArticleDTO.setCategoryIds(List.of(genRanUUID()));

        mockMvc.perform(post(RestApiConstants.API_ARTICLE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createArticleDTO)))
                .andExpect(status().isBadRequest())
                        .andExpect(result -> {
                            String response = result.getResponse().getContentAsString();
                            assert response.contains(ArticleErrorMessages.ARTICLE_DESCRIPTION_REQUIRED);
                        });

        verify(articleService, never()).createArticle(any());
    }

    @Test
    void createArticle_whenArticlePriceIsLessThanMin_shouldReturnBadRequest() throws Exception {
        createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setName("Smartphone");
        createArticleDTO.setDescription("A smartphone");
        createArticleDTO.setPrice(-1);
        createArticleDTO.setStock(10);
        createArticleDTO.setBrandId(genRanUUID());
        createArticleDTO.setCategoryIds(List.of(genRanUUID()));

        mockMvc.perform(post(RestApiConstants.API_ARTICLE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createArticleDTO)))
                .andExpect(status().isBadRequest())
                        .andExpect(result -> {
                            String response = result.getResponse().getContentAsString();
                            assert response.contains(ArticleErrorMessages.ARTICLE_PRICE_MIN_VALUE);
                        });

        verify(articleService, never()).createArticle(any());
    }

    @Test
    void createArticle_whenArticleStockIsLessThanMin_shouldReturnBadRequest() throws Exception {
        createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setName("Smartphone");
        createArticleDTO.setDescription("A smartphone");
        createArticleDTO.setPrice(1000);
        createArticleDTO.setStock(-1);
        createArticleDTO.setBrandId(genRanUUID());
        createArticleDTO.setCategoryIds(List.of(genRanUUID()));

        mockMvc.perform(post(RestApiConstants.API_ARTICLE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createArticleDTO)))
                .andExpect(status().isBadRequest())
                        .andExpect(result -> {
                            String response = result.getResponse().getContentAsString();
                            assert response.contains(ArticleErrorMessages.ARTICLE_STOCK_MIN_VALUE);
                        });

        verify(articleService, never()).createArticle(any());
    }

    @Test
    void createArticle_whenArticleBrandIdIsMissing_shouldReturnBadRequest() throws Exception {
        createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setName("Smartphone");
        createArticleDTO.setDescription("A smartphone");
        createArticleDTO.setPrice(1000);
        createArticleDTO.setStock(10);
        createArticleDTO.setCategoryIds(List.of(genRanUUID()));

        mockMvc.perform(post(RestApiConstants.API_ARTICLE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createArticleDTO)))
                .andExpect(status().isBadRequest())
                        .andExpect(result -> {
                            String response = result.getResponse().getContentAsString();
                            assert response.contains(ArticleErrorMessages.ARTICLE_BRAND_ID_REQUIRED);
                        });

        verify(articleService, never()).createArticle(any());
    }

    @Test
    void createArticle_whenArticleBrandIdIsInvalid_shouldReturnBadRequest() throws Exception {
        createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setName("Smartphone");
        createArticleDTO.setDescription("A smartphone");
        createArticleDTO.setPrice(1000);
        createArticleDTO.setStock(10);
        createArticleDTO.setBrandId("invalid-uuid");
        createArticleDTO.setCategoryIds(List.of(genRanUUID()));

        mockMvc.perform(post(RestApiConstants.API_ARTICLE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createArticleDTO)))
                .andExpect(status().isBadRequest())
                        .andExpect(result -> {
                            String response = result.getResponse().getContentAsString();
                            assert response.contains(ArticleErrorMessages.ARTICLE_BRAND_ID_NOT_VALID);
                        });

        verify(articleService, never()).createArticle(any());
    }

    @Test
    void createArticle_whenArticleCategoryIdsAreDuplicated_shouldReturnBadRequest() throws Exception {
        String categoryId = genRanUUID();
        createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setName("Smartphone");
        createArticleDTO.setDescription("A smartphone");
        createArticleDTO.setPrice(1000);
        createArticleDTO.setStock(10);
        createArticleDTO.setBrandId(genRanUUID());
        createArticleDTO.setCategoryIds(List.of(categoryId, categoryId));

        mockMvc.perform(post(RestApiConstants.API_ARTICLE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createArticleDTO)))
                .andExpect(status().isBadRequest())
                        .andExpect(result -> {
                            String response = result.getResponse().getContentAsString();
                            assert response.contains(ArticleErrorMessages.ARTICLE_DUPLICATED_CATEGORIES);
                        });

        verify(articleService, never()).createArticle(any());
    }

    @Test
    void createArticle_whenArticleCategoryIdsAreLessThanMin_shouldReturnBadRequest() throws Exception {
        createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setName("Smartphone");
        createArticleDTO.setDescription("A smartphone");
        createArticleDTO.setPrice(1000);
        createArticleDTO.setStock(10);
        createArticleDTO.setBrandId(genRanUUID());
        createArticleDTO.setCategoryIds(List.of());

        mockMvc.perform(post(RestApiConstants.API_ARTICLE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createArticleDTO)))
                .andExpect(status().isBadRequest())
                        .andExpect(result -> {
                            String response = result.getResponse().getContentAsString();
                            assert response.contains(ArticleErrorMessages.ARTICLE_INVALID_NUMBER_OF_CATEGORIES);
                        });

        verify(articleService, never()).createArticle(any());
    }

    @Test
    void createArticle_whenArticleCategoryIdsAreMoreThanMax_shouldReturnBadRequest() throws Exception {
        createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setName("Smartphone");
        createArticleDTO.setDescription("A smartphone");
        createArticleDTO.setPrice(1000);
        createArticleDTO.setStock(10);
        createArticleDTO.setBrandId(genRanUUID());
        createArticleDTO.setCategoryIds(List.of(genRanUUID(), genRanUUID(), genRanUUID(), genRanUUID(), genRanUUID(), genRanUUID()));

        mockMvc.perform(post(RestApiConstants.API_ARTICLE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createArticleDTO)))
                .andExpect(status().isBadRequest())
                        .andExpect(result -> {
                            String response = result.getResponse().getContentAsString();
                            assert response.contains(ArticleErrorMessages.ARTICLE_INVALID_NUMBER_OF_CATEGORIES);
                        });

        verify(articleService, never()).createArticle(any());
    }

    //TODO
    // createArticle_whenArticleCategoryIdsAreInvalid_shouldReturnBadRequest

    @Test
    void getAllArticles_whenQueryParamsAreValid_shouldReturnOk() throws Exception {
        mockMvc.perform(get(RestApiConstants.API_ARTICLE)
                .param("page", "1")
                .param("size", "10")
                .param("sort", "name,asc"))
                .andExpect(status().isOk());

        verify(articleService, times(1)).getAllArticles(any());
    }

    @Test
    void getAllArticles_whenPageParamIsInvalid_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get(RestApiConstants.API_ARTICLE)
                .param("page", "-1")
                .param("size", "10")
                .param("sort", "name,asc"))
                .andExpect(status().isBadRequest())
                        .andExpect(result -> {
                            String response = result.getResponse().getContentAsString();
                            assert response.contains(PaginatedResponseErrorMessages.PAGE_NUMBER_INVALID);
                        });

        verify(articleService, never()).getAllArticles(any());
    }

    @Test
    void getAllArticles_whenSizeParamIsInvalid_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get(RestApiConstants.API_ARTICLE)
                .param("page", "1")
                .param("size", "0")
                .param("sort", "name,asc"))
                .andExpect(status().isBadRequest())
                        .andExpect(result -> {
                            String response = result.getResponse().getContentAsString();
                            assert response.contains(PaginatedResponseErrorMessages.PAGE_SIZE_INVALID);
                        });

        verify(articleService, never()).getAllArticles(any());
    }

    @Test
    void getAllArticles_whenSortParamIsInvalid_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get(RestApiConstants.API_ARTICLE)
                .param("page", "1")
                .param("size", "10")
                .param("sort", "inva,lid"))
                .andExpect(status().isBadRequest())
                        .andExpect(result -> {
                            String response = result.getResponse().getContentAsString();
                            assert response.contains(PaginatedResponseErrorMessages.SORT_PARAM_INVALID);
                        });

        verify(articleService, never()).getAllArticles(any());
    }

}
