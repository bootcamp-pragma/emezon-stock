package com.emezon.stock.infra.outbound.mysql.jpa.repositories;

import com.emezon.stock.infra.outbound.mysql.jpa.constants.BrandEntityConstants;
import com.emezon.stock.infra.outbound.mysql.jpa.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IMySQLJPABrandRepository extends JpaRepository<BrandEntity, String> {

    @Query("SELECT b FROM " + BrandEntityConstants.ENTITY_NAME + " b WHERE LOWER(b.name)  = LOWER(:name)")
    Optional<BrandEntity> findByName(String name);

}
