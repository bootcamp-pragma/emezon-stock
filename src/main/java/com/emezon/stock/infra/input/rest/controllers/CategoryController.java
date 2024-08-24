package com.emezon.stock.infra.input.rest.controllers;

import com.emezon.stock.app.dtos.CreateCategoryDTO;
import com.emezon.stock.app.mappers.CreateCategoryDTOMapper;
import com.emezon.stock.app.services.CategoryService;
import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.models.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CreateCategoryDTO createCategoryDTO) {
        Category newCategory = CreateCategoryDTOMapper.toModel(createCategoryDTO);
        Category createdCategory = categoryService.createCategory(newCategory);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCategory.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdCategory);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<Category>> getAllCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PaginatedResponse<Category> categories = categoryService.getAllCategories(page, size);
        return ResponseEntity.ok(categories);
    }

}
