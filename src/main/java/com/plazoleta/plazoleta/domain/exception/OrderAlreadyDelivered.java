package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class OrderAlreadyDelivered extends ErrorException{

    public OrderAlreadyDelivered() {
        super(ExceptionConstants.ORDER_ALREADY_DELIVERED_ERROR, ExceptionConstants.ORDER_ALREADY_DELIVERED_MESSAGE, LocalDate.now().toString());
    }
}
