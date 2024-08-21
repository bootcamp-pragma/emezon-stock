package com.emezon.stock.domain.models;

import com.emezon.stock.domain.common.BaseModel;

import java.util.HashSet;
import java.util.Set;

public class Article extends BaseModel {

    private String name;
    private String description;
    private String code;
    private double price;
    private int stock;
    private Brand brand;
    private Set<Category> categories;

    public Article(String name, String description, String code, double price, int stock, Brand brand, Set<Category> categories) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.price = price;
        this.stock = stock;
        this.brand = brand;
        this.categories = categories;
    }

    public Article(String name, String description, String code, double price, int stock, Brand brand) {
        new Article(name, description, code, price, stock, brand, new HashSet<>());
    }

    public Article(String name, String description, String code, double price, int stock) {
        new Article(name, description, code, price, stock, null, new HashSet<>());
    }

    public Article(String name, String description, String code, double price, int stock, Set<Category> categories) {
        new Article(name, description, code, price, stock, null, categories);
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
