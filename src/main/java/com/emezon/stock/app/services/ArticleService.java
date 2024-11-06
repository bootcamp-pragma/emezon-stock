package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.app.dtos.article.ArticleListDTO;
import com.emezon.stock.app.dtos.article.CreateArticleDTO;
import com.emezon.stock.app.handlers.IArticleHandler;
import com.emezon.stock.app.mappers.ArticleDTOMapper;
import com.emezon.stock.domain.api.article.IPersistArticleInPort;
import com.emezon.stock.domain.api.article.IRetrieveArticleInPort;
import com.emezon.stock.domain.spi.IJwtService;
import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import com.emezon.stock.infra.inbound.rest.utils.PaginatedResponseUtils;
import com.emezon.stock.domain.models.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class ArticleService implements IArticleHandler {

    private final IPersistArticleInPort persistArticleInPort;
    private final IRetrieveArticleInPort retrieveArticleInPort;
    private final IJwtService jwtService;

    @Override
    public ArticleDTO createArticle(CreateArticleDTO article) {
        Article articleModel = ArticleDTOMapper.toModel(article);
        Article createdArticle = persistArticleInPort.createArticle(articleModel);
        return ArticleDTOMapper.toDTO(createdArticle);
    }

    @Override
    public ArticleDTO addSupply(String id, String payload) {
        Map<String, Object> payloadMap = jwtService.extractAllClaims(payload);
        Article updatedArticle = persistArticleInPort.addSupply(id, payloadMap);
        return ArticleDTOMapper.toDTO(updatedArticle);
    }

    @Override
    public ArticleDTO getArticleById(String id) {
        Optional<Article> article = retrieveArticleInPort.getArticleById(id);
        return article.map(ArticleDTOMapper::toDTO).orElse(null);
    }

    @Override
    public ArticleDTO getArticleByName(String name) {
        Optional<Article> article = retrieveArticleInPort.getArticleByName(name);
        return article.map(ArticleDTOMapper::toDTO).orElse(null);
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

    @Override
    public ArticleDTO updateRestockDate(String id, LocalDateTime restockDate) {
        if (restockDate != null) {
            restockDate = restockDate.withNano(0).withSecond(0).withMinute(0);
        }
        Article updatedArticle = persistArticleInPort.updateRestockDate(id, restockDate);
        return ArticleDTOMapper.toDTO(updatedArticle);
    }

}
