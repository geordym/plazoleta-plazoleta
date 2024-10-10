package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class UserIsNotRestaurantOwnerException extends ErrorException{

    public UserIsNotRestaurantOwnerException() {
        super(ExceptionConstants.INVALID_USER_ROLE_ERROR, ExceptionConstants.INVALID_USER_ROLE_MESSAGE, LocalDate.now().toString());
    }
}
