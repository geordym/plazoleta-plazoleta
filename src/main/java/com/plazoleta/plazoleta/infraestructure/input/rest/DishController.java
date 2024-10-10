package com.plazoleta.plazoleta.infraestructure.input.rest;


import com.plazoleta.plazoleta.application.dto.request.CreateDishRequestDto;
import com.plazoleta.plazoleta.application.handler.IDishHandler;
import com.plazoleta.plazoleta.domain.api.IDishServicePort;
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
@RequestMapping("/api/dish")
@RequiredArgsConstructor
public class DishController {

    private final IDishHandler dishHandler;

    @Operation(summary = "Create dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created succesfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Error in the body of the request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Dont have authorization to add dish to restaurant, ensure you are the owner of the restaurant", content = @Content),
    })
    @PostMapping
    private ResponseEntity<Void> createDish(@RequestBody @Valid CreateDishRequestDto createDishRequestDto){
        dishHandler.createDish(createDishRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}