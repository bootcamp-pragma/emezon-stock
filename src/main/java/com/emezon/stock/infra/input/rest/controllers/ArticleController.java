package com.emezon.stock.infra.input.rest.controllers;

import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.app.dtos.article.ArticleListDTO;
import com.emezon.stock.app.dtos.article.CreateArticleDTO;
import com.emezon.stock.app.services.ArticleService;
import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.ValidPageableRequest;
import com.emezon.stock.infra.constants.RestApiConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(RestApiConstants.API_ARTICLE)
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody @Valid CreateArticleDTO createArticleDTO) {
        ArticleDTO createdArticle = articleService.createArticle(createArticleDTO);
        URI location = URI.create(RestApiConstants.API_ARTICLE);
        return ResponseEntity.created(location).body(createdArticle);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<ArticleListDTO>> getAllArticles(
            @RequestParam @ValidPageableRequest @Valid
            MultiValueMap<String, String> queryParams
    ) {
        PaginatedResponse<ArticleListDTO> articles = articleService.getAllArticles(queryParams);
        return ResponseEntity.ok(articles);
    }

}
