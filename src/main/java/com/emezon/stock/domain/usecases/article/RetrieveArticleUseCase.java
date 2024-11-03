package com.emezon.stock.domain.usecases.article;

import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import com.emezon.stock.domain.constants.PaginatedResponseConstraints;
import com.emezon.stock.domain.models.Article;
import com.emezon.stock.domain.api.article.IRetrieveArticleInPort;
import com.emezon.stock.domain.spi.IArticleRepositoryOutPort;

import java.util.Optional;

public class RetrieveArticleUseCase implements IRetrieveArticleInPort {

    private final IArticleRepositoryOutPort articleRepositoryOutPort;

    public RetrieveArticleUseCase(IArticleRepositoryOutPort articleRepositoryOutPort) {
        this.articleRepositoryOutPort = articleRepositoryOutPort;
    }

    @Override
    public Optional<Article> getArticleById(String id) {
        return articleRepositoryOutPort.findById(id);
    }

    @Override
    public Optional<Article> getArticleByName(String name) {
        return articleRepositoryOutPort.findByName(name);
    }

    @Override
    public PaginatedResponse<Article> getAllArticles(PaginatedResponseParams params) {
        PaginatedResponseConstraints.validateParameters(params);
        return articleRepositoryOutPort.findAll(params);
    }
}
