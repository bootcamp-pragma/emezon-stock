package com.emezon.stock.infra.input.rest.controllers;

import com.emezon.stock.app.dtos.CategoryDTO;
import com.emezon.stock.app.dtos.CreateCategoryDTO;
import com.emezon.stock.app.services.CategoryService;
import com.emezon.stock.domain.common.classes.PaginatedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CreateCategoryDTO createCategoryDTO) {
        CategoryDTO createdCategory = categoryService.createCategory(createCategoryDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).body(createdCategory);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<CategoryDTO>> getAllCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "none") String sortDirection
    ) {
        PaginatedResponse<CategoryDTO> categories = categoryService.getAllCategories(page, size, sortDirection.toLowerCase().trim());
        return ResponseEntity.ok(categories);
    }

}
