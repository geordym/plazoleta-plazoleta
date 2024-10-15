package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class RestaurantPhoneAlreadyTaken extends ErrorException{

    public RestaurantPhoneAlreadyTaken() {
        super(ExceptionConstants.RESTAURANT_PHONE_ALREADY_TAKEN_ERROR, ExceptionConstants.RESTAURANT_PHONE_ALREADY_TAKEN_MESSAGE, LocalDate.now().toString());
    }

}
