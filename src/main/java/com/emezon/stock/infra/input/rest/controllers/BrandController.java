package com.emezon.stock.infra.input.rest.controllers;

import com.emezon.stock.app.dtos.BrandDTO;
import com.emezon.stock.app.dtos.CreateBrandDTO;
import com.emezon.stock.app.services.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandDTO> createBrand(@RequestBody @Valid CreateBrandDTO createBrandDTO) {
        BrandDTO createdBrand = brandService.createBrand(createBrandDTO);
        return ResponseEntity.ok(createdBrand);
    }

}
