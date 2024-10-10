package com.plazoleta.plazoleta.infraestructure.input.rest;


import com.plazoleta.plazoleta.application.dto.request.CreateRestaurantRequestDto;
import com.plazoleta.plazoleta.application.handler.IPlazoletaHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plazoleta")
@RequiredArgsConstructor
public class PlazoletaController {

    private final IPlazoletaHandler plazoletaHandler;

    @PostMapping("/restaurant")
    public ResponseEntity<Void> createRestaurant(@RequestBody @Valid CreateRestaurantRequestDto createRestaurantRequestDto){
        plazoletaHandler.createRestaurant(createRestaurantRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
