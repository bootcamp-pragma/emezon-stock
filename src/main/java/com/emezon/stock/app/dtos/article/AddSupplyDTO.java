package com.emezon.stock.app.dtos.article;

import com.emezon.stock.domain.constants.ArticleConstraints;
import com.emezon.stock.domain.constants.ArticleErrorMessages;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddSupplyDTO {

    @Min(value = ArticleConstraints.STOCK_MIN_VALUE, message = ArticleErrorMessages.ARTICLE_STOCK_MIN_VALUE)
    private int quantity;

}
