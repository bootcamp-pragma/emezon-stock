package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.app.dtos.article.CreateArticleDTO;
import com.emezon.stock.app.mappers.ArticleDTOMapper;
import com.emezon.stock.domain.models.Article;
import com.emezon.stock.domain.usecases.article.CreateArticleUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleService {

    private final CreateArticleUseCase createArticleUseCase;

    public ArticleDTO createArticle(CreateArticleDTO article) {
        Article articleModel = ArticleDTOMapper.toModel(article);
        Article createdArticle = createArticleUseCase.createArticle(articleModel);
        return ArticleDTOMapper.toDTO(createdArticle);
    }

}
