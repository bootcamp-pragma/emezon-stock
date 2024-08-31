package com.emezon.stock.app.dtos.article;

import com.emezon.stock.domain.constants.ArticleConstraints;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateArticleDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Min(ArticleConstraints.PRICE_MIN_VALUE)
    private double price;

    @Min(ArticleConstraints.STOCK_MIN_VALUE)
    private int stock;

    @NotBlank
    @UUID
    private String brandId;

    @UniqueElements(message = "The categories must be unique")
    @Size( min = ArticleConstraints.MIN_NUMBER_OF_CATEGORIES,
            max = ArticleConstraints.MAX_NUMBER_OF_CATEGORIES)
    private List<String> categoryIds;

}
