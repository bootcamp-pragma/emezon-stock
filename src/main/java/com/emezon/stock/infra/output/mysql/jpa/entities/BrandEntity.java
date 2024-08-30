package com.emezon.stock.infra.output.mysql.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "brands")
@Getter
@Setter
public class BrandEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true)
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
