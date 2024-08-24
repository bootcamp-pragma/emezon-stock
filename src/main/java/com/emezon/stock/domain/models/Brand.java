package com.emezon.stock.domain.models;

public class Brand {

    private String id;
    private String name;
    private String description;
    private String code;

    public Brand(String id, String name, String description, String code) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public Brand() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
