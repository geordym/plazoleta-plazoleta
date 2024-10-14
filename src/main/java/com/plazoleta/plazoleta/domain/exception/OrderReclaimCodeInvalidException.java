package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class OrderReclaimCodeInvalidException extends ErrorException{
    public OrderReclaimCodeInvalidException() {
        super(ExceptionConstants.ORDER_RECLAIM_CODE_INVALID_ERROR, ExceptionConstants.ORDER_RECLAIM_CODE_INVALID_MESSAGE, LocalDate.now().toString());
    }
}
