package com.emezon.stock.infra.output.mysql.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "brands")
@Data
public class BrandEntity {

    @Id
    @UuidGenerator
    private String id;

    private String name;

    private String description;

    private String code;

    @OneToMany(mappedBy = "brand")
    private List<ArticleEntity> articles;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
