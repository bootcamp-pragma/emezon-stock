package com.emezon.stock.infra.output.mysql.jpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "articles")
@Getter
@Setter
public class ArticleEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Min(0)
    private double price;

    @Column(nullable = false)
    @Min(0)
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    private BrandEntity brand;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "article_category",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"article_id", "category_id"})
    )
    private Set<CategoryEntity> categories;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
