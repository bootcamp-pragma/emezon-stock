package com.emezon.stock.app.dtos.article;

import com.emezon.stock.domain.constants.ArticleConstraints;
import com.emezon.stock.domain.constants.ArticleErrorMessages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = ArticleErrorMessages.ARTICLE_NAME_REQUIRED)
    private String name;

    @NotBlank(message = ArticleErrorMessages.ARTICLE_DESCRIPTION_REQUIRED)
    private String description;

    @Min(value = ArticleConstraints.PRICE_MIN_VALUE, message = ArticleErrorMessages.ARTICLE_PRICE_MIN_VALUE)
    private double price;

    @NotNull(message = ArticleErrorMessages.ARTICLE_BRAND_ID_REQUIRED)
    @NotBlank(message = ArticleErrorMessages.ARTICLE_BRAND_ID_NOT_VALID)
    @UUID(message = ArticleErrorMessages.ARTICLE_BRAND_ID_NOT_VALID)
    private String brandId;

    @UniqueElements(message = ArticleErrorMessages.ARTICLE_DUPLICATED_CATEGORIES)
    @Size( min = ArticleConstraints.MIN_NUMBER_OF_CATEGORIES,
            max = ArticleConstraints.MAX_NUMBER_OF_CATEGORIES,
            message = ArticleErrorMessages.ARTICLE_INVALID_NUMBER_OF_CATEGORIES)
    private List<String> categoryIds;

}
