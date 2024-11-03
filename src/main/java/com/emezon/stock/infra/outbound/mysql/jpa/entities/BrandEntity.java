package com.emezon.stock.infra.outbound.mysql.jpa.entities;

import com.emezon.stock.domain.constants.BrandConstraints;
import com.emezon.stock.infra.outbound.mysql.jpa.constants.BrandEntityConstants;
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

@Entity(name = BrandEntityConstants.ENTITY_NAME)
@Table(name = BrandEntityConstants.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true, nullable = false, length = BrandConstraints.NAME_MAX_LENGTH)
    private String name;

    @Column(nullable = false, length = BrandConstraints.DESCRIPTION_MAX_LENGTH)
    private String description;

    @OneToMany(mappedBy = "brand")
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
