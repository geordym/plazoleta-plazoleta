package com.plazoleta.plazoleta.domain.exception;

import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class UnauthorizedAccessException extends ErrorException{
    public UnauthorizedAccessException() {
        super(ExceptionConstants.UNAUTHORIZATED_ACCESS_ERROR, ExceptionConstants.UNAUTHORIZATED_ACCESS_ERROR_MESSAGE, LocalDate.now().toString());
    }
}
