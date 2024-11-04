package com.emezon.stock.app.handlers;

import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.app.dtos.article.ArticleListDTO;
import com.emezon.stock.app.dtos.article.CreateArticleDTO;
import com.emezon.stock.domain.utils.PaginatedResponse;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;

public interface IArticleHandler {

    ArticleDTO createArticle(CreateArticleDTO article);

    ArticleDTO addSupply(String id, int quantity);

    ArticleDTO getArticleById(String id);

    ArticleDTO getArticleByName(String name);

    PaginatedResponse<ArticleListDTO> getAllArticles(MultiValueMap<String, String> queryParams);

    ArticleDTO updateRestockDate(String id, LocalDateTime restockDate);

}
