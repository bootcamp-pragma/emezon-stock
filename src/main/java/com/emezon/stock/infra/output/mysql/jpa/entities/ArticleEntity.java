package com.emezon.stock.infra.output.mysql.jpa.entities;

import com.emezon.stock.domain.constants.ArticleConstraints;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    @Min(ArticleConstraints.PRICE_MIN_VALUE)
    private double price;

    @Column(nullable = false)
    @Min(ArticleConstraints.STOCK_MIN_VALUE)
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

    @Override
    public String toString() {
        List<String> categoriesStr = new ArrayList<>();
        for (CategoryEntity category : categories) {
            categoriesStr.add(category.toString());
        }
        return "\n{\n" +
                "id='" + id + "',\n" +
                "name='" + name + "',\n" +
                "description='" + description + "',\n" +
                "price=" + price + "\n" +
                "stock=" + stock + "\n" +
                "brand= " + brand.toString() + "\n" +
                "categories= [" + String.join(", ", categoriesStr) + " ]\n" +
                "}";
    }

}
