package com.emezon.stock.infra.output.mysql.jpa.repositories;

import com.emezon.stock.infra.output.mysql.jpa.entities.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMySQLJPAArticleRepository extends JpaRepository<ArticleEntity, String> {

    @Query("SELECT a FROM articles a WHERE LOWER(a.name) = LOWER(:name)")
    List<ArticleEntity> findByName(String name);

}
