package com.emezon.stock.infra.input.rest.controllers;

import com.emezon.stock.app.dtos.brand.BrandDTO;
import com.emezon.stock.app.dtos.brand.CreateBrandDTO;
import com.emezon.stock.app.services.BrandService;
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
@RequestMapping(RestApiConstants.API_BRAND)
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandDTO> createBrand(@RequestBody @Valid CreateBrandDTO createBrandDTO) {
        BrandDTO createdBrand = brandService.createBrand(createBrandDTO);
        URI location = URI.create(RestApiConstants.API_BRAND);
        return ResponseEntity.created(location).body(createdBrand);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<BrandDTO>> getAllBrands(
            @RequestParam @ValidPageableRequest @Valid
            MultiValueMap<String, String> queryParams
    ) {
        PaginatedResponse<BrandDTO> brands = brandService.getAllBrands(queryParams);
        return ResponseEntity.ok(brands);
    }

}
