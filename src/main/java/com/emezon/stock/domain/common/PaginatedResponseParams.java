package com.emezon.stock.domain.common;

import org.springframework.util.MultiValueMap;

import java.util.List;

public class PaginatedResponseParams {
    public int page;
    public int size;
    public List<String> sorting;

    public PaginatedResponseParams(int page, int size, List<String> sorting) {
        this.page = page;
        this.size = size;
        this.sorting = sorting;
    }

    public PaginatedResponseParams() {

    }

    public static void getFromMultiValueMap(MultiValueMap<String, String> queryParams) {
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

    public List<String> getSorting() {
        return sorting;
    }

    public void setSorting(List<String> sorting) {
        this.sorting = sorting;
    }
}
