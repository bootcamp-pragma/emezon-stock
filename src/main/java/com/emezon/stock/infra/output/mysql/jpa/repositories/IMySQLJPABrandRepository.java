package com.emezon.stock.infra.output.mysql.jpa.repositories;

import com.emezon.stock.infra.output.mysql.jpa.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IMySQLJPABrandRepository extends JpaRepository<BrandEntity, String> {

    @Query("SELECT b FROM brands b WHERE LOWER(b.name)  = LOWER(:name)")
    Optional<BrandEntity> findByName(String name);

}
