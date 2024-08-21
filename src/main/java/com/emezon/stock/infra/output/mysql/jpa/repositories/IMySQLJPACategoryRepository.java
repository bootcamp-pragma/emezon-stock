package com.emezon.stock.infra.output.mysql.jpa.repositories;

import com.emezon.stock.infra.output.mysql.jpa.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMySQLJPACategoryRepository extends JpaRepository<CategoryEntity, String> {
}
