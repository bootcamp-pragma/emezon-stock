package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.brand.BrandDTO;
import com.emezon.stock.app.dtos.brand.CreateBrandDTO;
import com.emezon.stock.app.handlers.IBrandHandler;
import com.emezon.stock.app.mappers.BrandDTOMapper;
import com.emezon.stock.domain.api.brand.IPersistBrandInPort;
import com.emezon.stock.domain.api.brand.IRetrieveBrandInPort;
import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import com.emezon.stock.infra.inbound.rest.utils.PaginatedResponseUtils;
import com.emezon.stock.domain.models.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BrandService implements IBrandHandler {

    private final IPersistBrandInPort iPersistBrandInPort;
    private final IRetrieveBrandInPort iRetrieveBrandInPort;

    @Override
    public BrandDTO createBrand(CreateBrandDTO brand) {
        Brand brandModel = BrandDTOMapper.toModel(brand);
        Brand createdBrand = iPersistBrandInPort.createBrand(brandModel);
        return BrandDTOMapper.toDTO(createdBrand);
    }

    @Override
    public BrandDTO getBrandById(String id) {
        Optional<Brand> brand = iRetrieveBrandInPort.getBrandById(id);
        return brand.map(BrandDTOMapper::toDTO).orElse(null);
    }

    @Override
    public BrandDTO getBrandByName(String name) {
        Optional<Brand> brand = iRetrieveBrandInPort.getBrandByName(name);
        return brand.map(BrandDTOMapper::toDTO).orElse(null);
    }

    @Override
    public PaginatedResponse<BrandDTO> getAllBrands(MultiValueMap<String, String> queryParams) {
        PaginatedResponseParams params = PaginatedResponseUtils.getFromMultiValueMap(queryParams);
        PaginatedResponse<Brand> brands = iRetrieveBrandInPort.getAllBrands(params);
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
