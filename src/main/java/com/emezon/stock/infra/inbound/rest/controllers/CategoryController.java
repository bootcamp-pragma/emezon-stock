package com.emezon.stock.infra.inbound.rest.controllers;

import com.emezon.stock.app.dtos.category.CategoryDTO;
import com.emezon.stock.app.dtos.category.CreateCategoryDTO;
import com.emezon.stock.app.handlers.ICategoryHandler;
import com.emezon.stock.infra.inbound.rest.utils.ValidPageableRequest;
import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.infra.inbound.rest.constants.PaginatedConstants;
import com.emezon.stock.infra.inbound.rest.constants.RestApiConstants;
import com.emezon.stock.infra.outbound.mysql.jpa.entities.CategoryEntity;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(RestApiConstants.API_CATEGORY)
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryHandler categoryHandler;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CreateCategoryDTO createCategoryDTO) {
        CategoryDTO createdCategory = categoryHandler.createCategory(createCategoryDTO);
        URI location = URI.create(com.emezon.stock.infra.inbound.rest.constants.RestApiConstants.API_CATEGORY);
        return ResponseEntity.created(location).body(createdCategory);
    }

    @GetMapping
    @Parameters({
            @Parameter(name = PaginatedConstants.PARAM_PAGE, description = PaginatedConstants.PARAM_PAGE_DESC, example = PaginatedConstants.PARAM_PAGE_EXAMPLE),
            @Parameter(name = PaginatedConstants.PARAM_SIZE, description = PaginatedConstants.PARAM_SIZE_DESC, example = PaginatedConstants.PARAM_SIZE_EXAMPLE),
            @Parameter(name = PaginatedConstants.PARAM_SORT, description = PaginatedConstants.PARAM_SORT_DESC,
                    array = @ArraySchema(schema = @Schema(type = "string", example = PaginatedConstants.PARAM_SORT_EXAMPLE)))
    })
    public ResponseEntity<PaginatedResponse<CategoryDTO>> getAllCategories(
            @Parameter(hidden = true) @RequestParam @ValidPageableRequest(target = CategoryEntity.class) @Valid
            MultiValueMap<String, String> queryParams
    ) {
        PaginatedResponse<CategoryDTO> categories = categoryHandler.getAllCategories(queryParams);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
        CategoryDTO category = categoryHandler.getCategoryByName(name);
        return ResponseEntity.ok(category);
    }

}
