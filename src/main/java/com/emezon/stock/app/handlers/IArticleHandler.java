package com.emezon.stock.app.handlers;

import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.app.dtos.article.ArticleListDTO;
import com.emezon.stock.app.dtos.article.CreateArticleDTO;
import com.emezon.stock.domain.utils.PaginatedResponse;
import org.springframework.util.MultiValueMap;

public interface IArticleHandler {

    public ArticleDTO createArticle(CreateArticleDTO article);

    public ArticleDTO addSupply(String id, int quantity);

    public ArticleDTO getArticleById(String id);

    public ArticleDTO getArticleByName(String name);

    public PaginatedResponse<ArticleListDTO> getAllArticles(MultiValueMap<String, String> queryParams);

}
