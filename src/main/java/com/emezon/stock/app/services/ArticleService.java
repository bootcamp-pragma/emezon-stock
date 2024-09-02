package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.app.dtos.article.CreateArticleDTO;
import com.emezon.stock.app.dtos.common.ReqPageableAndSortableDTO;
import com.emezon.stock.app.mappers.ArticleDTOMapper;
import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.constants.PaginatedResponseConstraints;
import com.emezon.stock.domain.models.Article;
import com.emezon.stock.domain.usecases.article.CreateArticleUseCase;
import com.emezon.stock.domain.usecases.article.RetrieveArticleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class ArticleService {

    private final CreateArticleUseCase createArticleUseCase;
    private final RetrieveArticleUseCase retrieveArticleUseCase;

    public ArticleDTO createArticle(CreateArticleDTO article) {
        Article articleModel = ArticleDTOMapper.toModel(article);
        Article createdArticle = createArticleUseCase.createArticle(articleModel);
        return ArticleDTOMapper.toDTO(createdArticle);
    }

    public Optional<ArticleDTO> getArticleById(String id) {
        Optional<Article> article = retrieveArticleUseCase.getArticleById(id);
        return article.map(ArticleDTOMapper::toDTO);
    }

    public PaginatedResponse<ArticleDTO> getAllArticles(MultiValueMap<String, String> queryParams) {
        int page = queryParams.containsKey("page") ?
                Integer.parseInt(Objects.requireNonNull(queryParams.getFirst("page"))) :
                PaginatedResponseConstraints.DEFAULT_PAGE_NUMBER;
        int size = queryParams.containsKey("size") ?
                Integer.parseInt(Objects.requireNonNull(queryParams.getFirst("size"))) :
                PaginatedResponseConstraints.DEFAULT_PAGE_SIZE;
        List<String> sort = queryParams.containsKey("sort") ?
                queryParams.get("sort") : List.of();
        PaginatedResponse<Article> articles = retrieveArticleUseCase.getAllArticles(page, size, sort);
        List<ArticleDTO> articleDTOS = articles.getItems().stream().map(ArticleDTOMapper::toDTO).toList();
        return new PaginatedResponse<>(
                articleDTOS,
                articles.getPage(),
                articles.getSize(),
                articles.getTotalItems(),
                articles.getTotalPages()
        );
    }

}
