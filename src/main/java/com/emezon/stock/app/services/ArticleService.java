package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.app.dtos.article.ArticleListDTO;
import com.emezon.stock.app.dtos.article.CreateArticleDTO;
import com.emezon.stock.app.handlers.IArticleHandler;
import com.emezon.stock.app.mappers.ArticleDTOMapper;
import com.emezon.stock.domain.api.article.IPersistArticleInPort;
import com.emezon.stock.domain.api.article.IRetrieveArticleInPort;
import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import com.emezon.stock.domain.utils.PaginatedResponseUtils;
import com.emezon.stock.domain.models.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ArticleService implements IArticleHandler {

    private final IPersistArticleInPort createArticleInPort;
    private final IRetrieveArticleInPort retrieveArticleInPort;

    @Override
    public ArticleDTO createArticle(CreateArticleDTO article) {
        Article articleModel = ArticleDTOMapper.toModel(article);
        Article createdArticle = createArticleInPort.createArticle(articleModel);
        return ArticleDTOMapper.toDTO(createdArticle);
    }

    @Override
    public Optional<ArticleDTO> getArticleById(String id) {
        Optional<Article> article = retrieveArticleInPort.getArticleById(id);
        return article.map(ArticleDTOMapper::toDTO);
    }

    @Override
    public PaginatedResponse<ArticleListDTO> getAllArticles(MultiValueMap<String, String> queryParams) {
        PaginatedResponseParams params = PaginatedResponseUtils.getFromMultiValueMap(queryParams);
        PaginatedResponse<Article> articles = retrieveArticleInPort.getAllArticles(params);
        List<ArticleListDTO> articleDTOS = articles.getItems().stream().map(ArticleDTOMapper::toListDTO).toList();
        return new PaginatedResponse<>(
                articleDTOS,
                articles.getPage(),
                articles.getSize(),
                articles.getTotalItems(),
                articles.getTotalPages()
        );
    }

}
