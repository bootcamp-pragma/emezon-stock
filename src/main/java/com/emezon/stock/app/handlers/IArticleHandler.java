package com.emezon.stock.app.handlers;

import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.app.dtos.article.ArticleListDTO;
import com.emezon.stock.app.dtos.article.CreateArticleDTO;
import com.emezon.stock.domain.utils.PaginatedResponse;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

public interface IArticleHandler {

    public ArticleDTO createArticle(CreateArticleDTO article);

    public Optional<ArticleDTO> getArticleById(String id);

    public Optional<ArticleDTO> getArticleByName(String name);

    public PaginatedResponse<ArticleListDTO> getAllArticles(MultiValueMap<String, String> queryParams);

}
