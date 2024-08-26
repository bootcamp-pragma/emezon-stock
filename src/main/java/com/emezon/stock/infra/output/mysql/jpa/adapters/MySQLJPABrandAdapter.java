package com.emezon.stock.infra.output.mysql.jpa.adapters;


import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.ports.outbound.IBrandRepositoryOutPort;
import com.emezon.stock.infra.output.mysql.jpa.entities.BrandEntity;
import com.emezon.stock.infra.output.mysql.jpa.mappers.BrandEntityMapper;
import com.emezon.stock.infra.output.mysql.jpa.repositories.IMySQLJPABrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MySQLJPABrandAdapter implements IBrandRepositoryOutPort {

    private final IMySQLJPABrandRepository repository;

    @Override
    public Brand save(Brand brand) {
        BrandEntity brandEntity = BrandEntityMapper.toEntity(brand);
        BrandEntity savedBrandEntity = repository.save(brandEntity);
        return BrandEntityMapper.toModel(savedBrandEntity);
    }

    @Override
    public Optional<Brand> findById(String id) {
        Optional<BrandEntity> brandEntity = repository.findById(id);
        return brandEntity.map(BrandEntityMapper::toModel);
    }

    @Override
    public Optional<Brand> findByName(String name) {
        Optional<BrandEntity> brandEntity = repository.findByName(name);
        return brandEntity.map(BrandEntityMapper::toModel);
    }

}
