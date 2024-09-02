package com.emezon.stock.app.dtos.common;

import com.emezon.stock.domain.constants.PaginatedResponseConstraints;
import com.emezon.stock.domain.constants.PaginatedResponseErrorMessages;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ReqPageableAndSortableDTO {

    @Min(value = PaginatedResponseConstraints.PAGE_NUMBER_MIN,
            message = PaginatedResponseErrorMessages.PAGE_NUMBER_INVALID)
    private Integer page;

    @Min(value = PaginatedResponseConstraints.PAGE_SIZE_MIN,
            message = PaginatedResponseErrorMessages.PAGE_SIZE_INVALID)
    private Integer size;

    private MultiValueMap<String, String> sort;

    public ReqPageableAndSortableDTO(Integer page, Integer size, MultiValueMap<String, String> sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    @PostConstruct
    public void init() {
        if (this.page == null) {
            this.page = PaginatedResponseConstraints.DEFAULT_PAGE_NUMBER;
        }
        if (this.size == null) {
            this.size = PaginatedResponseConstraints.DEFAULT_PAGE_SIZE;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : this.sort.entrySet()) {
            List<String> values = entry.getValue();
            sb.append(entry.getKey()).append(": [");
            for (String value : values) {
                sb.append(value).append("*");
            }
            sb.append("]");
        }
        return "ReqPageableAndSortableDTO{" +
                "page=" + page +
                ", size=" + size +
                ", params=" + sb +
                '}';
    }

}
