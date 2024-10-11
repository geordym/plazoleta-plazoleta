package com.plazoleta.plazoleta.application.handler.impl;

import com.plazoleta.plazoleta.application.dto.request.CreateDishRequestDto;
import com.plazoleta.plazoleta.application.dto.request.ToggleDishStatusRequestDto;
import com.plazoleta.plazoleta.application.dto.request.UpdateDishRequestDto;
import com.plazoleta.plazoleta.application.handler.IDishHandler;
import com.plazoleta.plazoleta.application.mapper.IDishRequestMapper;
import com.plazoleta.plazoleta.domain.api.IDishServicePort;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.infraestructure.out.jpa.mapper.IDishEntityMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishHandlerImpl implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;

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

}
