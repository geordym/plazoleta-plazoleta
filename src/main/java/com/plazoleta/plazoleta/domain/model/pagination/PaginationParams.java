package com.plazoleta.plazoleta.domain.model.pagination;


import com.plazoleta.plazoleta.domain.enums.RestaurantSortBy;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaginationParams<E extends Enum<E>> {
    private int page;
    private int size;
    private E sortBy;
    private boolean ascending;
}
