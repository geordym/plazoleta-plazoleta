package com.plazoleta.plazoleta.application.handler.impl;

import com.plazoleta.plazoleta.application.dto.request.CreateRestaurantRequestDto;
import com.plazoleta.plazoleta.application.dto.response.RestaurantShortResponseDto;
import com.plazoleta.plazoleta.application.handler.IPlazoletaHandler;
import com.plazoleta.plazoleta.application.mapper.IRestaurantRequestMapper;
import com.plazoleta.plazoleta.application.mapper.IRestaurantResponseMapper;
import com.plazoleta.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.plazoleta.domain.enums.RestaurantSortBy;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;
import com.plazoleta.plazoleta.domain.usecase.RestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
public class PlazoletaHandlerImpl implements IPlazoletaHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    @Override
    public void createRestaurant(CreateRestaurantRequestDto createRestaurantRequestDto) {
        restaurantServicePort.createRestaurant(restaurantRequestMapper.toModel(createRestaurantRequestDto));
    }

    @Override
    public PaginationCustom<RestaurantShortResponseDto> listRestaurants(int page, int size, String sortBy, boolean ascending) {
        RestaurantSortBy sortByEnum = RestaurantSortBy.fromValue(sortBy);
        PaginationCustom<Restaurant> restaurantList = restaurantServicePort.listRestaurants(new PaginationParams(page, size, sortByEnum, ascending));
        return restaurantResponseMapper.toPaginationDto(restaurantList);
    }


}
