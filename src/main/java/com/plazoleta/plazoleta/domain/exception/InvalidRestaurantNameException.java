package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class InvalidRestaurantNameException extends ErrorException{


    public InvalidRestaurantNameException() {
        super(ExceptionConstants.INVALID_RESTAURANT_NAME_ERROR, ExceptionConstants.INVALID_RESTAURANT_NAME_MESSAGE, LocalDate.now().toString());
    }
}
