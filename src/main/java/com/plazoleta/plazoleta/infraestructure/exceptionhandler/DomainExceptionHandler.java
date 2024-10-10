package com.plazoleta.plazoleta.infraestructure.exceptionhandler;


import com.plazoleta.plazoleta.application.dto.response.ErrorGenericResponseDto;
import com.plazoleta.plazoleta.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DomainExceptionHandler {

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleRUnauthorizedAccessException(UnauthorizedAccessException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleRestaurantNotFoundException(RestaurantNotFoundException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(UserIsNotRestaurantOwnerException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleUserIsNotRestaurantOwnerException(UserIsNotRestaurantOwnerException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleUserDoesNotExistException(UserDoesNotExistException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(InvalidRestaurantNameException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleInvalidRestaurantNameException(InvalidRestaurantNameException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleInvalidPhoneNumberException(InvalidPhoneNumberException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorGenericResponseDto);
    }


    @ExceptionHandler(ExternalConnectionException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleExternalConnectionException(ExternalConnectionException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.GATEWAY_TIMEOUT)
                .body(errorGenericResponseDto);
    }


}
