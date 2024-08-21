package com.emezon.stock.app.dtos;

import lombok.Data;

@Data
public class CreateCategoryDTO {

    private String name;
    private String description;
    private String code;

}
