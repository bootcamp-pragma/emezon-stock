package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.brand.BrandDTO;
import com.emezon.stock.app.dtos.brand.CreateBrandDTO;
import com.emezon.stock.app.mappers.BrandDTOMapper;
import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.common.PaginatedResponseParams;
import com.emezon.stock.domain.common.PaginatedResponseUtils;
import com.emezon.stock.domain.constants.PaginatedResponseConstraints;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.usecases.brand.CreateBrandUseCase;
import com.emezon.stock.domain.usecases.brand.RetrieveBrandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class BrandService {

    private final CreateBrandUseCase createBrandUseCase;
    private final RetrieveBrandUseCase retrieveBrandUseCase;

    public BrandDTO createBrand(CreateBrandDTO brand) {
        Brand brandModel = BrandDTOMapper.toModel(brand);
        Brand createdBrand = createBrandUseCase.createBrand(brandModel);
        return BrandDTOMapper.toDTO(createdBrand);
    }

    public Optional<BrandDTO> getBrandById(String id) {
        Optional<Brand> brand = retrieveBrandUseCase.getBrandById(id);
        return brand.map(BrandDTOMapper::toDTO);
    }

    public Optional<BrandDTO> getBrandByName(String name) {
        Optional<Brand> brand = retrieveBrandUseCase.getBrandByName(name);
        return brand.map(BrandDTOMapper::toDTO);
    }

    public PaginatedResponse<BrandDTO> getAllBrands(MultiValueMap<String, String> queryParams) {
        PaginatedResponseParams params = PaginatedResponseUtils.getFromMultiValueMap(queryParams);
        PaginatedResponse<Brand> brands = retrieveBrandUseCase.getAllBrands(params);
        List<BrandDTO> brandDTOs = brands.getItems().stream()
                .map(BrandDTOMapper::toDTO)
                .toList();
        return new PaginatedResponse<>(
                brandDTOs,
                brands.getPage(),
                brands.getSize(),
                brands.getTotalItems(),
                brands.getTotalPages()
        );
    }

}
