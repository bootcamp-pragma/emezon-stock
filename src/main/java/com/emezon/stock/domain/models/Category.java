package com.emezon.stock.domain.models;

import com.emezon.stock.domain.common.BaseModel;

import java.util.HashSet;
import java.util.Set;

public class Category extends BaseModel {

    private String name;
    private String description;
    private String code;
    private Set<Article> articles;

    public Category(String name, String description, String code, Set<Article> articles) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.articles = articles;
    }

    public Category(String name, String description, String code) {
        new Category(name, description, code, new HashSet<>());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
