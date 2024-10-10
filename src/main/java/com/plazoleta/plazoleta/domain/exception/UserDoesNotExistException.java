package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class UserDoesNotExistException extends ErrorException{

    public UserDoesNotExistException() {
        super(ExceptionConstants.USER_DOESNOT_EXIST_ERROR, ExceptionConstants.USER_DOESNOT_EXIST_MESSAGE, LocalDate.now().toString());
    }
}
