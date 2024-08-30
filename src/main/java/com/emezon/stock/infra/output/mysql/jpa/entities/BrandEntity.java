package com.emezon.stock.infra.output.mysql.jpa.entities;

import jakarta.persistence.*;
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

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 120)
    private String description;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<ArticleEntity> articles;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
