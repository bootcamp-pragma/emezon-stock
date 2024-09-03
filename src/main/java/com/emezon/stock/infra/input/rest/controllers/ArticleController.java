package com.emezon.stock.infra.input.rest.controllers;

import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.app.dtos.article.ArticleListDTO;
import com.emezon.stock.app.dtos.article.CreateArticleDTO;
import com.emezon.stock.app.services.ArticleService;
import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.utils.annotations.ValidPageableRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody @Valid CreateArticleDTO createArticleDTO) {
        ArticleDTO createdArticle = articleService.createArticle(createArticleDTO);
        return ResponseEntity.ok(createdArticle);
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
