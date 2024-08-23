package com.emezon.stock.infra.output.mysql.jpa.repositories;

import com.emezon.stock.infra.output.mysql.jpa.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IMySQLJPACategoryRepository extends JpaRepository<CategoryEntity, String> {

    @Query("SELECT c FROM categories c WHERE LOWER(c.name)  = LOWER(:name)")
    CategoryEntity findByName(String name);

    @Query("SELECT c FROM categories c WHERE LOWER(c.code)  = LOWER(:code)")
    CategoryEntity findByCode(String code);

}
