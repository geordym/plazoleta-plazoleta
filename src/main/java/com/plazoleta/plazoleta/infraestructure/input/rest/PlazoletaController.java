package com.plazoleta.plazoleta.infraestructure.input.rest;


import com.plazoleta.plazoleta.application.dto.request.CreateRestaurantRequestDto;
import com.plazoleta.plazoleta.application.dto.response.RestaurantShortResponseDto;
import com.plazoleta.plazoleta.application.handler.IPlazoletaHandler;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plazoleta")
@RequiredArgsConstructor
public class PlazoletaController {

    private final IPlazoletaHandler plazoletaHandler;


    @Operation(summary = "List restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content),
    })
    @GetMapping("/restaurant")
    public ResponseEntity<PaginationCustom<RestaurantShortResponseDto>> listRestaurants(@RequestParam(defaultValue = "0") int page,
                                                                                        @RequestParam(defaultValue = "10") int size,
                                                                                        @RequestParam(defaultValue = "name") String sortBy,
                                                                                        @RequestParam(defaultValue = "true") boolean ascending){
        PaginationCustom<RestaurantShortResponseDto> restaurantList = plazoletaHandler.listRestaurants(page, size, sortBy, ascending);
        return new ResponseEntity<>(restaurantList, HttpStatus.OK);
    }



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
