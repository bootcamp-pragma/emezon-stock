package com.emezon.stock.infra.input.rest.controllers;

import com.emezon.stock.app.dtos.CreateCategoryDTO;
import com.emezon.stock.app.mappers.CreateCategoryDTOMapper;
import com.emezon.stock.app.services.CategoryService;
import com.emezon.stock.domain.models.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

}
