package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class DishNotFoundException extends ErrorException{


    public DishNotFoundException() {
        super(ExceptionConstants.DISH_NOT_FOUND_ERROR, ExceptionConstants.DISH_NOT_FOUND_ERROR_MESSAGE, LocalDate.now().toString());
    }
}
