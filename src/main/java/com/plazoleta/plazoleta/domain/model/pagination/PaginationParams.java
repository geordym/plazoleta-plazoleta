package com.plazoleta.plazoleta.domain.model.pagination;


import com.plazoleta.plazoleta.domain.enums.RestaurantSortBy;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaginationParams {
    private int page;
    private int size;
    private RestaurantSortBy sortBy;
    private boolean ascending;
}
