package com.plazoleta.plazoleta.domain.exception;


import com.plazoleta.plazoleta.domain.util.ExceptionConstants;

import java.time.LocalDateTime;

public class InvalidNitException extends ErrorException{


    public InvalidNitException() {
        super(ExceptionConstants.INVALID_NIT_ERROR, ExceptionConstants.INVALID_NIT_MESSAGE, LocalDateTime.now().toString());
    }

}
