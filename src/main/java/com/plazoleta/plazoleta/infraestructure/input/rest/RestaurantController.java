package com.plazoleta.plazoleta.infraestructure.input.rest;


import com.plazoleta.plazoleta.application.dto.response.RestaurantResponseDto;
import com.plazoleta.plazoleta.application.handler.IRestaurantHandler;
import com.plazoleta.plazoleta.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final IRestaurantHandler restaurantHandler;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(@PathVariable Long restaurantId){
        RestaurantResponseDto restaurantResponseDto = restaurantHandler.findRestaurantById(restaurantId);
        return new ResponseEntity<>(restaurantResponseDto, HttpStatus.OK);
    }


    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantByOwnerId(@PathVariable Long ownerId){
        RestaurantResponseDto restaurantResponseDto = restaurantHandler.findRestaurantByOwnerId(ownerId);
        return new ResponseEntity<>(restaurantResponseDto, HttpStatus.OK);
    }


}
