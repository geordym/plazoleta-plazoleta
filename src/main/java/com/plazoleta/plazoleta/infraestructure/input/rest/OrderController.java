package com.plazoleta.plazoleta.infraestructure.input.rest;


import com.plazoleta.plazoleta.application.dto.request.CreateDishRequestDto;
import com.plazoleta.plazoleta.application.dto.request.CreateOrderRequestDto;
import com.plazoleta.plazoleta.application.dto.response.OrderResponseDto;
import com.plazoleta.plazoleta.application.dto.response.RestaurantShortResponseDto;
import com.plazoleta.plazoleta.application.handler.IOrderHandler;
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
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderHandler orderHandler;

    @Operation(summary = "List orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content),
    })
    @GetMapping("/employee")
    public ResponseEntity<PaginationCustom<OrderResponseDto>> listRestaurants(@RequestParam(defaultValue = "") String status,
                                                                              @RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "10") int size,
                                                                              @RequestParam(defaultValue = "status") String sortBy,
                                                                              @RequestParam(defaultValue = "true") boolean ascending){
        PaginationCustom<OrderResponseDto> orderList = orderHandler.findOrdersByEmployeeRestaurant(status, page, size, sortBy, ascending);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }


    @Operation(summary = "Assign order to employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order assigned to employee succesfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Error in the body of the request", content = @Content),
            @ApiResponse(responseCode = "404", description = "The order cannot be founded", content = @Content),
            @ApiResponse(responseCode = "409", description = "The order dont is in PENDING status", content = @Content),
    })
    @PostMapping("/assign/{orderId}")
    private ResponseEntity<Void> assignOrderToEmployee(@PathVariable Long orderId){
        orderHandler.assignOrderToEmployee(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Create order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created succesfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Error in the body of the request", content = @Content),
            @ApiResponse(responseCode = "409", description = "You cannot make an order if have an order at the moment", content = @Content),
    })
    @PostMapping
    private ResponseEntity<Void> createOrder(@RequestBody @Valid CreateOrderRequestDto createOrderRequestDto){
        orderHandler.createOrder(createOrderRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
