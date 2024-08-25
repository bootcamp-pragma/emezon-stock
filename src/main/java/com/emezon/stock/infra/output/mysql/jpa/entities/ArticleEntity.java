package com.emezon.stock.infra.output.mysql.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "articles")
@Data
public class ArticleEntity {

    @Id
    @UuidGenerator
    private String id;

    private String name;

    private String description;

    private String code;

    private double price;

    private int stock;

    @ManyToOne
    private BrandEntity brand;

    @ManyToMany
    @JoinTable
    private List<CategoryEntity> categories;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
