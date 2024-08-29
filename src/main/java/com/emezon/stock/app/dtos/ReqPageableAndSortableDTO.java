package com.emezon.stock.app.dtos;

import com.emezon.stock.domain.common.constants.PaginatedResponseConstraints;
import com.emezon.stock.domain.common.constants.PaginatedResponseErrorMessages;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReqPageableAndSortableDTO {

    @Min(value = 0, message = PaginatedResponseErrorMessages.PAGE_NUMBER_INVALID)
    private Integer page;

    @Min(value = 1, message = PaginatedResponseErrorMessages.PAGE_SIZE_INVALID)
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
        for (String key : this.sort.keySet()) {
            List<String> values = this.sort.get(key);
            sb.append(key).append(": [");
            for (String value : values) {
                sb.append(value).append("*");
            }
            sb.append("]");
        }
        return "ReqPageableAndSortableDTO{" +
                "page=" + page +
                ", size=" + size +
                ", params=" + sb.toString() +
                '}';
    }

}
