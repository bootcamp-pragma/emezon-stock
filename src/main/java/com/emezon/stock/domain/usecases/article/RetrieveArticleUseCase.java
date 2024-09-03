package com.emezon.stock.domain.usecases.article;

import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.constants.PaginatedResponseConstraints;
import com.emezon.stock.domain.models.Article;
import com.emezon.stock.domain.ports.inbound.article.IRetrieveArticleInPort;
import com.emezon.stock.domain.ports.outbound.IArticleRepositoryOutPort;

import java.util.List;
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
    public PaginatedResponse<Article> getAllArticles(int page, int size, List<String> sorting) {
        PaginatedResponseConstraints.validateParameters(page, size, sorting);
        return articleRepositoryOutPort.findAll(page, size, sorting);
    }
}
