package com.emezon.stock.infra.output.mysql.jpa.mappers;

import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.infra.output.mysql.jpa.entities.BrandEntity;

import java.util.List;
import java.util.stream.Collectors;

public class BrandEntityMapper {

    private BrandEntityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static BrandEntity toEntity(Brand brand) {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(brand.getId());
        brandEntity.setName(brand.getName());
        brandEntity.setDescription(brand.getDescription());
        return brandEntity;
    }

    public static Brand toModel(BrandEntity brandEntity) {
        Brand brand = new Brand();
        brand.setId(brandEntity.getId());
        brand.setName(brandEntity.getName());
        brand.setDescription(brandEntity.getDescription());
        return brand;
    }

    public static List<Brand> toModels(List<BrandEntity> brandEntities) {
        return brandEntities.stream().map(BrandEntityMapper::toModel).collect(Collectors.toList());
    }

}
