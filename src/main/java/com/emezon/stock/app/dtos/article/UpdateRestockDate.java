package com.emezon.stock.app.dtos.article;

import com.emezon.stock.domain.constants.ArticleErrorMessages;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRestockDate {

    @NotNull(message = ArticleErrorMessages.ARTICLE_RESTOCK_DATE_REQUIRED)
//    @JsonFormat(pattern = ArticleConstraints.RESTOCK_DATE_PATTERN, shape = JsonFormat.Shape.STRING)
    private LocalDateTime restockDate;

}
