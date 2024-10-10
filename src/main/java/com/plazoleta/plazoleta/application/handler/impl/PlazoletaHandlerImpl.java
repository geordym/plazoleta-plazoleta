package com.plazoleta.plazoleta.application.handler.impl;

import com.plazoleta.plazoleta.application.dto.request.CreateRestaurantRequestDto;
import com.plazoleta.plazoleta.application.handler.IPlazoletaHandler;
import com.plazoleta.plazoleta.application.mapper.IRestaurantRequestMapper;
import com.plazoleta.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.plazoleta.domain.usecase.RestaurantUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlazoletaHandlerImpl implements IPlazoletaHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;

    @Override
    public void createRestaurant(CreateRestaurantRequestDto createRestaurantRequestDto) {
        restaurantServicePort.createRestaurant(restaurantRequestMapper.toModel(createRestaurantRequestDto));
    }


}
