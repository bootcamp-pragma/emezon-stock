package com.emezon.stock.infra.inbound.rest.controllers;

import com.emezon.stock.app.dtos.article.AddSupplyDTO;
import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.app.dtos.article.ArticleListDTO;
import com.emezon.stock.app.dtos.article.CreateArticleDTO;
import com.emezon.stock.app.handlers.IArticleHandler;
import com.emezon.stock.domain.models.external.UserRoles;
import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.ValidPageableRequest;
import com.emezon.stock.infra.inbound.rest.constants.PaginatedConstants;
import com.emezon.stock.infra.inbound.rest.constants.RestApiConstants;
import com.emezon.stock.infra.outbound.mysql.jpa.entities.ArticleEntity;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(RestApiConstants.API_ARTICLE)
@RequiredArgsConstructor
public class ArticleController {

    private final IArticleHandler articleHandler;

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody @Valid CreateArticleDTO createArticleDTO) {
        ArticleDTO createdArticle = articleHandler.createArticle(createArticleDTO);
        URI location = URI.create(com.emezon.stock.infra.inbound.rest.constants.RestApiConstants.API_ARTICLE);
        return ResponseEntity.created(location).body(createdArticle);
    }

    @GetMapping
    @Parameters({
            @Parameter(name = PaginatedConstants.PARAM_PAGE, description = PaginatedConstants.PARAM_PAGE_DESC, example = PaginatedConstants.PARAM_PAGE_EXAMPLE),
            @Parameter(name = PaginatedConstants.PARAM_SIZE, description = PaginatedConstants.PARAM_SIZE_DESC, example = PaginatedConstants.PARAM_SIZE_EXAMPLE),
            @Parameter(name = PaginatedConstants.PARAM_SORT, description = PaginatedConstants.PARAM_SORT_DESC,
                    array = @ArraySchema(schema = @Schema(type = "string", example = PaginatedConstants.PARAM_SORT_EXAMPLE)))
    })
    public ResponseEntity<PaginatedResponse<ArticleListDTO>> getAllArticles(
            @Parameter(hidden = true) @RequestParam @ValidPageableRequest(target = ArticleEntity.class) @Valid
            MultiValueMap<String, String> queryParams
    ) {
        PaginatedResponse<ArticleListDTO> articles = articleHandler.getAllArticles(queryParams);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ArticleDTO> getArticleByName(@PathVariable String name) {
        ArticleDTO article = articleHandler.getArticleByName(name);
        return ResponseEntity.ok(article);
    }

    @PreAuthorize("hasAnyRole(@securityConstants.ADD_SUPPLY_ROLES)")
    @PatchMapping("/{id}/add-supply")
    public ResponseEntity<ArticleDTO> addSupply(@PathVariable String id, @RequestBody @Valid AddSupplyDTO addSupplyDTO) {
        ArticleDTO updatedArticle = articleHandler.addSupply(id, addSupplyDTO.getQuantity());
        return ResponseEntity.ok(updatedArticle);
    }

}
