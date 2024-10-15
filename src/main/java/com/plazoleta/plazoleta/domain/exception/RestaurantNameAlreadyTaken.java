package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class RestaurantNameAlreadyTaken extends ErrorException{


    public RestaurantNameAlreadyTaken() {
        super(ExceptionConstants.RESTAURANT_NAME_ALREADY_TAKEN_ERROR, ExceptionConstants.RESTAURANT_NAME_ALREADY_TAKEN_MESSAGE, LocalDate.now().toString());
    }

}
