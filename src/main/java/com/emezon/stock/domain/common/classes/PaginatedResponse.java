package com.emezon.stock.domain.common.classes;

import java.util.List;

public class PaginatedResponse <T> {

    private List<T> elements;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public PaginatedResponse(List<T> elements, int page, int size, long totalElements, int totalPages) {
        this.elements = elements;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public PaginatedResponse() {
        // Empty constructor
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
