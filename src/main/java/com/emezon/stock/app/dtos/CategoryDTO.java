package com.emezon.stock.app.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private String id;
    private String name;
    private String description;
    private String code;

    public CategoryDTO(String id, String name, String description, String code) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
    }

}
