package com.emezon.stock.infra.outbound.mysql.jpa.mappers;

import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.infra.outbound.mysql.jpa.entities.BrandEntity;

import java.util.List;

public class BrandEntityMapper {

    private BrandEntityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static BrandEntity toEntity(Brand brand) {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(brand.getId());
        brandEntity.setName(brand.getName());
        brandEntity.setDescription(brand.getDescription());
        brandEntity.setCreatedAt(brand.getCreatedAt());
        brandEntity.setUpdatedAt(brand.getUpdatedAt());
        return brandEntity;
    }

    public static Brand toModel(BrandEntity brandEntity) {
        Brand brand = new Brand();
        brand.setId(brandEntity.getId());
        brand.setName(brandEntity.getName());
        brand.setDescription(brandEntity.getDescription());
        brand.setCreatedAt(brandEntity.getCreatedAt());
        brand.setUpdatedAt(brandEntity.getUpdatedAt());
        return brand;
    }

    public static List<Brand> toModels(List<BrandEntity> brandEntities) {
        return brandEntities.stream().map(BrandEntityMapper::toModel).toList();
    }

}
