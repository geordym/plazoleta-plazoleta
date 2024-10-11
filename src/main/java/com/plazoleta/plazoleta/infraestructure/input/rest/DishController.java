package com.plazoleta.plazoleta.infraestructure.input.rest;


import com.plazoleta.plazoleta.application.dto.request.CreateDishRequestDto;
import com.plazoleta.plazoleta.application.dto.request.ToggleDishStatusRequestDto;
import com.plazoleta.plazoleta.application.dto.request.UpdateDishRequestDto;
import com.plazoleta.plazoleta.application.dto.response.DishResponseDto;
import com.plazoleta.plazoleta.application.dto.response.RestaurantShortResponseDto;
import com.plazoleta.plazoleta.application.handler.IDishHandler;
import com.plazoleta.plazoleta.domain.api.IDishServicePort;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
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
@RequestMapping("/api/dish")
@RequiredArgsConstructor
public class DishController {

    private final IDishHandler dishHandler;


    @Operation(summary = "List dishes of restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content),
    })
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<PaginationCustom<DishResponseDto>> listRestaurants(
            @RequestParam(value = "category", defaultValue = "") String categoryFilter,
            @PathVariable("restaurantId") Long restaurantId,
                                                                                        @RequestParam(defaultValue = "0") int page,
                                                                                         @RequestParam(defaultValue = "10") int size,
                                                                                        @RequestParam(defaultValue = "name") String sortBy,
                                                                                        @RequestParam(defaultValue = "true") boolean ascending){
        PaginationCustom<DishResponseDto> dishList = dishHandler.listDishesOfResturant(categoryFilter, restaurantId, page, size, sortBy, ascending);
        return new ResponseEntity<>(dishList, HttpStatus.OK);
    }


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

    @Operation(summary = "Update dish description and price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish updated succesfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Error in the body of the request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Dont have authorization to modify price dish of that restaurant, ensure you are the owner of the restaurant", content = @Content),
    })
    @PutMapping
    private ResponseEntity<Void> updateDish(@RequestBody @Valid UpdateDishRequestDto updateDishRequestDto){
        dishHandler.updateDish(updateDishRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Change dish status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish status changed succesfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Error in the body of the request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Dont have authorization to modify price dish of that restaurant, ensure you are the owner of the restaurant", content = @Content),
    })
    @PatchMapping("/status")
    private ResponseEntity<Void> toggleDishStatus(@RequestBody @Valid ToggleDishStatusRequestDto updateDishRequestDto){
        dishHandler.toogleDishStatus(updateDishRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
