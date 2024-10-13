package com.plazoleta.plazoleta.application.handler.impl;

import com.plazoleta.plazoleta.application.dto.response.RestaurantResponseDto;
import com.plazoleta.plazoleta.application.handler.IRestaurantHandler;
import com.plazoleta.plazoleta.application.mapper.IRestaurantResponseMapper;
import com.plazoleta.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantHandlerImpl implements IRestaurantHandler {


    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    @Override
    public RestaurantResponseDto findRestaurantByOwnerId(Long ownerId) {
        Restaurant restaurant = restaurantServicePort.findRestaurantByOwnerId(ownerId);
        return restaurantResponseMapper.toDto(restaurant);
    }

    @Override
    public RestaurantResponseDto findRestaurantById(Long restaurantId) {
        Restaurant restaurant = restaurantServicePort.findRestaurantById(restaurantId);
        return restaurantResponseMapper.toDto(restaurant);
    }
}
