package com.emezon.stock.infra.outbound.mysql.jpa.repositories;

import com.emezon.stock.infra.outbound.mysql.jpa.constants.ArticleEntityConstants;
import com.emezon.stock.infra.outbound.mysql.jpa.entities.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMySQLJPAArticleRepository extends JpaRepository<ArticleEntity, String> {

    @Query("SELECT a FROM " + ArticleEntityConstants.ENTITY_NAME + " a WHERE LOWER(a.name) = LOWER(:name)")
    List<ArticleEntity> findByName(String name);

}
