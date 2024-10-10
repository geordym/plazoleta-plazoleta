package com.plazoleta.plazoleta.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorException extends RuntimeException{
    public String error;
    public String message;
    public String timestamps;

}