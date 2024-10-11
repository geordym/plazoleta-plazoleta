package com.plazoleta.plazoleta.application.handler.impl;

import com.plazoleta.plazoleta.application.dto.request.CreateDishRequestDto;
import com.plazoleta.plazoleta.application.dto.request.ToggleDishStatusRequestDto;
import com.plazoleta.plazoleta.application.dto.request.UpdateDishRequestDto;
import com.plazoleta.plazoleta.application.dto.response.DishResponseDto;
import com.plazoleta.plazoleta.application.handler.IDishHandler;
import com.plazoleta.plazoleta.application.mapper.IDishRequestMapper;
import com.plazoleta.plazoleta.application.mapper.IDishResponseMapper;
import com.plazoleta.plazoleta.domain.api.IDishServicePort;
import com.plazoleta.plazoleta.domain.enums.DishEnumSortBy;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;
import com.plazoleta.plazoleta.domain.util.DishFilter;
import com.plazoleta.plazoleta.infraestructure.out.jpa.mapper.IDishEntityMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishHandlerImpl implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

    @Override
    public void createDish(CreateDishRequestDto createDishRequestDto) {
        Dish dish = dishRequestMapper.toModel(createDishRequestDto);
        dishServicePort.createDish(dish);
    }

    @Override
    public void updateDish(UpdateDishRequestDto updateDishRequestDto) {
        Long dishId = updateDishRequestDto.getDishId();
        Integer quantity = updateDishRequestDto.getPrice();
        String description = updateDishRequestDto.getDescription();
        dishServicePort.updateDish(dishId, quantity, description);
    }

    @Override
    public void toogleDishStatus(ToggleDishStatusRequestDto toggleDishStatusRequestDto) {
        Long dishId = toggleDishStatusRequestDto.getDishId();
        boolean active = toggleDishStatusRequestDto.getActive();
        dishServicePort.toogleDishStatus(dishId, active);
    }

    @Override
    public PaginationCustom<DishResponseDto> listDishesOfResturant(String categoryFilter, Long restaurantId, int page, int size, String sortBy, boolean ascending) {
        DishEnumSortBy dishEnumSortBy = DishEnumSortBy.fromValue(sortBy);
        PaginationCustom<Dish> dishPagination = dishServicePort.listDishesOfRestaurant(new DishFilter(categoryFilter), restaurantId,new PaginationParams<>(page, size, dishEnumSortBy, ascending));
        return dishResponseMapper.toPaginationDto(dishPagination);
    }

}
