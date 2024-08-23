package com.emezon.stock.domain.models;

import java.util.HashSet;
import java.util.Set;

public class Brand {

    private String id;
    private String name;
    private String description;
    private String code;
    private Set<Article> articles;

    public Brand(String id, String name, String description, String code, Set<Article> articles) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.articles = articles;
    }

    public Brand(String id, String name, String description, String code) {
        new Brand(id, name, description, code, new HashSet<>());
    }

    public Brand() {
        new Brand(null, null, null, null, new HashSet<>());
    }

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

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
