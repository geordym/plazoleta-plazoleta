package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDateTime;

public class CustomerHasActiveOrderException extends ErrorException {
    public CustomerHasActiveOrderException() {
        super(ExceptionConstants.ORDER_IN_PROGRESS_ERROR, ExceptionConstants.ORDER_IN_PROGRESS_MESSAGE, LocalDateTime.now().toString());
    }


 /*   public OrderAlreadyInProgressException(Long orderId, Long restaurantId) {
        super(ExceptionConstants.DISH_IN_PROGRESS_ERROR, buildMessage(orderId, restaurantId),  LocalDateTime.now().toString());
    }

    private static String buildMessage(Long orderId, Long restaurantId) {
        return String.format(ExceptionConstants.DISH_IN_PROGRESS_MESSAGE, orderId, restaurantId);
    }*/

}
