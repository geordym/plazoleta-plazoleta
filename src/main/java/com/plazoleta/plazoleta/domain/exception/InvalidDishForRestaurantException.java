package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDateTime;

public class InvalidDishForRestaurantException extends ErrorException {

    public InvalidDishForRestaurantException(Long dishId, Long restaurantId) {
        super(ExceptionConstants.DISH_NOT_FOUND_ERROR, buildMessage(dishId, restaurantId),  LocalDateTime.now().toString());
    }

    private static String buildMessage(Long dishId, Long restaurantId) {
        return String.format(ExceptionConstants.DISH_NOT_FOUND_IN_RESTAURANT_MESSAGE, dishId, restaurantId);
    }

}
