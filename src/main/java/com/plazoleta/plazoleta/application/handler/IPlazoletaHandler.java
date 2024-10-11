package com.plazoleta.plazoleta.application.handler;

import com.plazoleta.plazoleta.application.dto.request.CreateRestaurantRequestDto;
import com.plazoleta.plazoleta.application.dto.response.RestaurantShortResponseDto;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;

import java.util.List;

public interface IPlazoletaHandler {

    void createRestaurant(CreateRestaurantRequestDto createRestaurantRequestDto);

    PaginationCustom<RestaurantShortResponseDto> listRestaurants(int page, int size, String sortBy, boolean ascending);

}
