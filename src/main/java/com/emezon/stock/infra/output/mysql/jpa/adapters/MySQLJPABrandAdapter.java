package com.emezon.stock.infra.output.mysql.jpa.adapters;


import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.ports.outbound.IBrandRepositoryOutPort;
import com.emezon.stock.infra.output.mysql.jpa.entities.BrandEntity;
import com.emezon.stock.infra.output.mysql.jpa.mappers.BrandEntityMapper;
import com.emezon.stock.infra.output.mysql.jpa.repositories.IMySQLJPABrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public PaginatedResponse<Brand> findAll(int page, int size, List<String> sorting) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String sort : sorting) {
            String[] sortArr = sort.split(",");
            if (sortArr.length == 2) {
                Sort.Order order = new Sort.Order(Sort.Direction.fromString(sortArr[1]), sortArr[0]);
                orders.add(order);
            }
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        Page<BrandEntity> pageRes = repository.findAll(pageable);
        PaginatedResponse<Brand> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setItems(BrandEntityMapper.toModels(pageRes.getContent()));
        paginatedResponse.setTotalItems(pageRes.getTotalElements());
        paginatedResponse.setTotalPages(pageRes.getTotalPages());
        paginatedResponse.setPage(pageRes.getNumber());
        paginatedResponse.setSize(pageRes.getSize());
        return paginatedResponse;
    }

}
