package com.emezon.stock.infra.input.rest.controllers;

import com.emezon.stock.app.dtos.category.CategoryDTO;
import com.emezon.stock.app.dtos.category.CreateCategoryDTO;
import com.emezon.stock.app.handlers.ICategoryHandler;
import com.emezon.stock.domain.utils.ValidPageableRequest;
import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.infra.constants.RestApiConstants;
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
        URI location = URI.create(RestApiConstants.API_CATEGORY);
        return ResponseEntity.created(location).body(createdCategory);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<CategoryDTO>> getAllCategories(
            @RequestParam @ValidPageableRequest @Valid
            MultiValueMap<String, String> queryParams
    ) {
        PaginatedResponse<CategoryDTO> categories = categoryHandler.getAllCategories(queryParams);
        return ResponseEntity.ok(categories);
    }

}
