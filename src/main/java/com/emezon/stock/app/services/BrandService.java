package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.BrandDTO;
import com.emezon.stock.app.dtos.CreateBrandDTO;
import com.emezon.stock.app.mappers.CreateBrandDTOMapper;
import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.common.constants.PaginatedResponseConstraints;
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
        Brand brandModel = CreateBrandDTOMapper.toModel(brand);
        Brand createdBrand = createBrandUseCase.createBrand(brandModel);
        return CreateBrandDTOMapper.toDTO(createdBrand);
    }

    public Optional<BrandDTO> getBrandById(String id) {
        Optional<Brand> brand = retrieveBrandUseCase.getBrandById(id);
        return brand.map(CreateBrandDTOMapper::toDTO);
    }

    public Optional<BrandDTO> getBrandByName(String name) {
        Optional<Brand> brand = retrieveBrandUseCase.getBrandByName(name);
        return brand.map(CreateBrandDTOMapper::toDTO);
    }

    public PaginatedResponse<BrandDTO> getAllBrands(MultiValueMap<String, String> queryParams) {
        int page = queryParams.containsKey("page") ?
                Integer.parseInt(Objects.requireNonNull(queryParams.getFirst("page"))) :
                PaginatedResponseConstraints.DEFAULT_PAGE_NUMBER;
        int size = queryParams.containsKey("size") ?
                Integer.parseInt(Objects.requireNonNull(queryParams.getFirst("size"))) :
                PaginatedResponseConstraints.DEFAULT_PAGE_SIZE;
        List<String> sort = queryParams.containsKey("sort") ?
                queryParams.get("sort") : List.of();

        PaginatedResponse<Brand> brands = retrieveBrandUseCase.getAllBrands(page, size, sort);
        return new PaginatedResponse<>(
                CreateBrandDTOMapper.toDTOList(brands.getItems()),
                brands.getPage(),
                brands.getSize(),
                brands.getTotalItems(),
                brands.getTotalPages()
        );
    }

}
