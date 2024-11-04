package com.emezon.stock.app.dtos.article;

import com.emezon.stock.app.dtos.brand.BrandDTO;
import com.emezon.stock.app.dtos.category.CategoryIdNameDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ArticleListDTO {

    private String id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private BrandDTO brand;
    private List<CategoryIdNameDTO> categories;
    private LocalDateTime restockDate;

}
