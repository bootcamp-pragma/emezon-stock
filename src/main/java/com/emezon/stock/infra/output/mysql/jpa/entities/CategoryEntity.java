package com.emezon.stock.infra.output.mysql.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "categories")
@Data
public class CategoryEntity {

    @Id
    @UuidGenerator
    private String id;

    private String name;

    private String description;

    private String code;

    @ManyToMany(mappedBy = "categories")
    private Set<ArticleEntity> articles;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
