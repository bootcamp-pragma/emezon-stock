package com.emezon.stock.app.dtos.article;

import com.emezon.stock.app.dtos.brand.BrandDTO;
import com.emezon.stock.app.dtos.category.CategoryDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleDTO {

    private String name;
    private String description;
    private double price;
    private int stock;
    private BrandDTO brand;
    private List<CategoryDTO> categories;


}
