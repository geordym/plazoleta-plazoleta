package com.plazoleta.plazoleta.infraestructure.exceptionhandler;


import com.plazoleta.plazoleta.application.dto.response.ErrorGenericResponseDto;
import com.plazoleta.plazoleta.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DomainExceptionHandler {

    @ExceptionHandler(InvalidDishForRestaurantException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleInvalidDishForRestaurantException(InvalidDishForRestaurantException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(InvalidNitException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleInvalidNitException(InvalidNitException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(RestaurantPhoneAlreadyTaken.class)
    public ResponseEntity<ErrorGenericResponseDto> handleRestaurantPhoneAlreadyTaken(RestaurantPhoneAlreadyTaken ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(RestaurantNitAlreadyTaken.class)
    public ResponseEntity<ErrorGenericResponseDto> handleRestaurantNitAlreadyTaken(RestaurantNitAlreadyTaken ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(RestaurantNameAlreadyTaken.class)
    public ResponseEntity<ErrorGenericResponseDto> handleRestaurantNameAlreadyTaken(RestaurantNameAlreadyTaken ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }


    @ExceptionHandler(CustomerHasActiveOrderException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleCustomerHasActiveOrderException(CustomerHasActiveOrderException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(OrderAlreadyCanceled.class)
    public ResponseEntity<ErrorGenericResponseDto> handleOrderAlreadyCanceled(OrderAlreadyCanceled ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(OrderNotCancelableException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleOrderNotCancelableException(OrderNotCancelableException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(OrderAlreadyDelivered.class)
    public ResponseEntity<ErrorGenericResponseDto> handleOrderAlreadyDelivered(OrderAlreadyDelivered ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(OrderNotReadyException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleOrderNotReadyException(OrderNotReadyException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(OrderReclaimCodeInvalidException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleOrderReclaimCodeInvalidException(OrderReclaimCodeInvalidException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(OrderReclaimCodeNotMatchesException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleOrderReclaimCodeException(OrderReclaimCodeNotMatchesException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(OrderNotPreparingException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleOrderNotPreparingException(OrderNotPreparingException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(OrderNotPendingException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleOrderNotPendingException(OrderNotPendingException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleOrderNotFoundException(OrderNotFoundException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(DishNotFoundException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleDishNotFoundException(DishNotFoundException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorGenericResponseDto);
    }

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
