package com.emezon.stock.app.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {

    private String id;
    private String name;
    private String description;
    private String code;

}
