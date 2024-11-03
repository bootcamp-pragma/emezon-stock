package com.emezon.stock.infra.inbound.rest.utils;

import com.emezon.stock.domain.constants.PaginatedResponseConstraints;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Objects;

public class PaginatedResponseUtils {

    public static int getPageFromMultivaluedMap(MultiValueMap<String, String> queryParams) {
        try {
            return Integer.parseInt(Objects.requireNonNull(queryParams.getFirst("page")));
        } catch (NumberFormatException | NullPointerException e) {
            return PaginatedResponseConstraints.DEFAULT_PAGE_NUMBER;
        }
    }

    public static int getSizeFromMultivaluedMap(MultiValueMap<String, String> queryParams) {
        try {
            return Integer.parseInt(Objects.requireNonNull(queryParams.getFirst("size")));
        } catch (NumberFormatException | NullPointerException e) {
            return PaginatedResponseConstraints.DEFAULT_PAGE_SIZE;
        }
    }

    public static List<String> getSortListFromMultivaluedMap(MultiValueMap<String, String> queryParams) {
        try {
            List<String> list = Objects.requireNonNull(queryParams.get("sort"));
            return list.isEmpty() ? PaginatedResponseConstraints.DEFAULT_SORT : list;
        } catch (ClassCastException | NullPointerException e) {
            return PaginatedResponseConstraints.DEFAULT_SORT;
        }
    }

    public static PaginatedResponseParams getFromMultiValueMap(MultiValueMap<String, String> queryParams) {
        PaginatedResponseParams p = new PaginatedResponseParams();
        p.setPage(getPageFromMultivaluedMap(queryParams));
        p.setSize(getSizeFromMultivaluedMap(queryParams));
        p.setSorting(getSortListFromMultivaluedMap(queryParams));
        return p;
    }

    private PaginatedResponseUtils() {
        throw new IllegalStateException("Utility class");
    }

}
