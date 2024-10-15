package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class RestaurantNitAlreadyTaken extends ErrorException{

    public RestaurantNitAlreadyTaken() {
        super(ExceptionConstants.RESTAURANT_NIT_ALREADY_TAKEN_ERROR, ExceptionConstants.RESTAURANT_NIT_ALREADY_TAKEN_MESSAGE, LocalDate.now().toString());
    }

}
