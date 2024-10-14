package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class OrderNotPreparingException extends ErrorException{

    public OrderNotPreparingException() {
        super(ExceptionConstants.ORDER_NOT_PREPARING_ERROR, ExceptionConstants.ORDER_NOT_PREPARING_MESSAGE, LocalDate.now().toString());
    }
}
