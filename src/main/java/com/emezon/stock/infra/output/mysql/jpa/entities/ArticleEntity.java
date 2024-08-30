package com.emezon.stock.infra.output.mysql.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "articles")
@Getter
@Setter
public class ArticleEntity {

    @Id
    @UuidGenerator
    private String id;

    private String name;

    private String description;

    private String code;

    private double price;

    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    private BrandEntity brand;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable
    private List<CategoryEntity> categories;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
