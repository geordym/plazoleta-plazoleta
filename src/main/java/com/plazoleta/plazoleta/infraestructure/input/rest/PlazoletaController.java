package com.plazoleta.plazoleta.infraestructure.input.rest;


import com.plazoleta.plazoleta.application.dto.request.CreateRestaurantRequestDto;
import com.plazoleta.plazoleta.application.handler.IPlazoletaHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Create restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created succesfully", content = @Content),
            @ApiResponse(responseCode = "403", description = "Error in the body of the request", content = @Content),
    })
    @PostMapping("/restaurant")
    public ResponseEntity<Void> createRestaurant(@RequestBody @Valid CreateRestaurantRequestDto createRestaurantRequestDto){
        plazoletaHandler.createRestaurant(createRestaurantRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
