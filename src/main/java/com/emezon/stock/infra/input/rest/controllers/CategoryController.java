package com.emezon.stock.infra.input.rest.controllers;

import com.emezon.stock.app.dtos.category.CategoryDTO;
import com.emezon.stock.app.dtos.category.CreateCategoryDTO;
import com.emezon.stock.app.services.CategoryService;
import com.emezon.stock.domain.utils.annotations.ValidPageableRequest;
import com.emezon.stock.domain.common.PaginatedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CreateCategoryDTO createCategoryDTO) {
        CategoryDTO createdCategory = categoryService.createCategory(createCategoryDTO);
        URI location = URI.create("/category");
        return ResponseEntity.created(location).body(createdCategory);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<CategoryDTO>> getAllCategories(
            @RequestParam @ValidPageableRequest @Valid
            MultiValueMap<String, String> queryParams
    ) {
        PaginatedResponse<CategoryDTO> categories = categoryService.getAllCategories(queryParams);
        return ResponseEntity.ok(categories);
    }

}
