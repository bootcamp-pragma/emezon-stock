package com.emezon.stock.infra.output.mysql.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "categories")
@Getter
@Setter
public class CategoryEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 90)
    private String description;

    @Column(unique = true, nullable = false)
    private String code;

    @ManyToMany(mappedBy = "categories")
    private List<ArticleEntity> articles;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "\n{\n" +
                "id='" + id + "'\n" +
                "name='" + name + "'\n" +
                "description='" + description + "'\n" +
                "code='" + code + "'\n" +
                "}";
    }

}
