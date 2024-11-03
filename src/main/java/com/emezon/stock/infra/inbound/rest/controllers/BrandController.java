package com.emezon.stock.infra.inbound.rest.controllers;

import com.emezon.stock.app.dtos.brand.BrandDTO;
import com.emezon.stock.app.dtos.brand.CreateBrandDTO;
import com.emezon.stock.app.handlers.IBrandHandler;
import com.emezon.stock.domain.utils.ValidPageableRequest;
import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.infra.inbound.rest.constants.PaginatedConstants;
import com.emezon.stock.infra.inbound.rest.constants.RestApiConstants;
import com.emezon.stock.infra.outbound.mysql.jpa.entities.BrandEntity;
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
@RequestMapping(RestApiConstants.API_BRAND)
@RequiredArgsConstructor
public class BrandController {

    private final IBrandHandler brandHandler;

    @PostMapping
    public ResponseEntity<BrandDTO> createBrand(@RequestBody @Valid CreateBrandDTO createBrandDTO) {
        BrandDTO createdBrand = brandHandler.createBrand(createBrandDTO);
        URI location = URI.create(com.emezon.stock.infra.inbound.rest.constants.RestApiConstants.API_BRAND);
        return ResponseEntity.created(location).body(createdBrand);
    }

    @GetMapping
    @Parameters({
            @Parameter(name = PaginatedConstants.PARAM_PAGE, description = PaginatedConstants.PARAM_PAGE_DESC, example = PaginatedConstants.PARAM_PAGE_EXAMPLE),
            @Parameter(name = PaginatedConstants.PARAM_SIZE, description = PaginatedConstants.PARAM_SIZE_DESC, example = PaginatedConstants.PARAM_SIZE_EXAMPLE),
            @Parameter(name = PaginatedConstants.PARAM_SORT, description = PaginatedConstants.PARAM_SORT_DESC,
                    array = @ArraySchema(schema = @Schema(type = "string", example = PaginatedConstants.PARAM_SORT_EXAMPLE)))
    })
    public ResponseEntity<PaginatedResponse<BrandDTO>> getAllBrands(
            @Parameter(hidden = true) @RequestParam @ValidPageableRequest(target = BrandEntity.class) @Valid
            MultiValueMap<String, String> queryParams
    ) {
        PaginatedResponse<BrandDTO> brands = brandHandler.getAllBrands(queryParams);
        return ResponseEntity.ok(brands);
    }

}
