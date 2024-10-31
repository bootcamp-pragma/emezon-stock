package com.emezon.stock.infra.output.mysql.jpa.repositories;

import com.emezon.stock.infra.output.mysql.jpa.constants.CategoryEntityConstants;
import com.emezon.stock.infra.output.mysql.jpa.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IMySQLJPACategoryRepository extends JpaRepository<CategoryEntity, String> {

    @Query("SELECT c FROM " + CategoryEntityConstants.ENTITY_NAME + " c WHERE LOWER(c.name)  = LOWER(:name)")
    Optional<CategoryEntity> findByName(String name);

}
