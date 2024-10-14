package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class OrderNotCancelableException extends ErrorException{


    public OrderNotCancelableException() {
        super(ExceptionConstants.ORDER_NOT_CANCELABLE_ERROR, ExceptionConstants.ORDER_NOT_CANCELABLE_MESSAGE, LocalDate.now().toString());
    }
}
