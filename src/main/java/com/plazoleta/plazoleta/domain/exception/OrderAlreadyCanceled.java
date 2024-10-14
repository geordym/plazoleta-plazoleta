package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class OrderAlreadyCanceled extends ErrorException{


    public OrderAlreadyCanceled() {
        super(ExceptionConstants.ORDER_ALREADY_CANCELED_ERROR, ExceptionConstants.ORDER_ALREADY_CANCELED_MESSAGE, LocalDate.now().toString());
    }
}
