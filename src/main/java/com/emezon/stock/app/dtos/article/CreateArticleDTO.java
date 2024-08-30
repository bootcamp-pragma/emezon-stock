package com.emezon.stock.app.dtos.article;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateArticleDTO {

    private String name;
    private String description;
    private double price;
    private int stock;
    private String brandId;
    private List<String> categoryIds;

}
