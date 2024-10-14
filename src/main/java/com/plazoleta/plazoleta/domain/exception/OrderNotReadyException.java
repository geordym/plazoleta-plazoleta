package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class OrderNotReadyException extends ErrorException{


    public OrderNotReadyException() {
        super(ExceptionConstants.ORDER_NOT_READY_ERROR, ExceptionConstants.ORDER_NOT_READY_MESSAGE, LocalDate.now().toString());
    }
}
