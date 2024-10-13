package com.plazoleta.plazoleta.application.handler;

import com.plazoleta.plazoleta.application.dto.response.RestaurantResponseDto;

public interface IRestaurantHandler {


    RestaurantResponseDto findRestaurantByOwnerId(Long ownerId);
    RestaurantResponseDto findRestaurantById(Long restaurantId);

}
