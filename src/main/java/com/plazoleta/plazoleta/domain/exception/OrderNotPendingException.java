package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class OrderNotPendingException extends ErrorException{


    public OrderNotPendingException() {
        super(ExceptionConstants.ORDER_NOT_PENDING_ERROR, ExceptionConstants.ORDER_NOT_PENDING_MESSAGE, LocalDate.now().toString());
    }
}
