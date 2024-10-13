package com.plazoleta.plazoleta.domain.model.pagination;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PaginationCustom<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLast;

    public PaginationCustom() {
    }

    public PaginationCustom(List<T> content, int pageNumber, int pageSize, long totalElements, int totalPages, boolean isLast) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.isLast = isLast;
    }

    public PaginationCustom(List<T> content, int pageNumber) {
        this(content, pageNumber, 0, 0, 0, true); // Constructor simplificado
    }

}