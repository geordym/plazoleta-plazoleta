package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class OrderReclaimCodeNotMatchesException extends ErrorException{

    public OrderReclaimCodeNotMatchesException() {
        super(ExceptionConstants.ORDER_RECLAIM_CODE_ERROR, ExceptionConstants.ORDER_RECLAIM_CODE_MESSAGE, LocalDate.now().toString());
    }
}
