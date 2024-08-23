package com.emezon.stock.domain.models;

import java.util.HashSet;
import java.util.Set;

public class Article {

    private String id;
    private String name;
    private String description;
    private String code;
    private double price;
    private int stock;
    private Brand brand;
    private Set<Category> categories;

    public Article(String id, String name, String description, String code, double price, int stock, Brand brand, Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.price = price;
        this.stock = stock;
        this.brand = brand;
        this.categories = categories;
    }

    public Article(String id, String name, String description, String code, double price, int stock, Brand brand) {
        new Article(id, name, description, code, price, stock, brand, new HashSet<>());
    }

    public Article(String id, String name, String description, String code, double price, int stock) {
        new Article(id, name, description, code, price, stock, null, new HashSet<>());
    }

    public Article(String id, String name, String description, String code, double price, int stock, Set<Category> categories) {
        new Article(id, name, description, code, price, stock, null, categories);
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

}
