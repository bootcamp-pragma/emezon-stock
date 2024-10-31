package com.emezon.stock.infra.output.mysql.jpa.entities;

import com.emezon.stock.domain.constants.CategoryConstraints;
import com.emezon.stock.infra.output.mysql.jpa.constants.CategoryEntityConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = CategoryEntityConstants.ENTITY_NAME)
@Table(name = CategoryEntityConstants.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true, nullable = false, length = CategoryConstraints.NAME_MAX_LENGTH)
    private String name;

    @Column(nullable = false, length = CategoryConstraints.DESCRIPTION_MAX_LENGTH)
    private String description;

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
                "}";
    }

}
