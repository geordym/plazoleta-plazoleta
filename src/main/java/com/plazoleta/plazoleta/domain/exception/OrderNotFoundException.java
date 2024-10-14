package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class OrderNotFoundException extends ErrorException{


    public OrderNotFoundException() {
        super(ExceptionConstants.ORDER_NOT_FOUND_ERROR, ExceptionConstants.ORDER_NOT_FOUND_MESSAGE, LocalDate.now().toString());
    }
}
