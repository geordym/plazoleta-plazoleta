package com.plazoleta.plazoleta.application.handler;

import com.plazoleta.plazoleta.application.dto.request.CreateDishRequestDto;
import com.plazoleta.plazoleta.application.dto.request.ToggleDishStatusRequestDto;
import com.plazoleta.plazoleta.application.dto.request.UpdateDishRequestDto;

public interface IDishHandler {

    void createDish(CreateDishRequestDto createDishRequestDto);
    void updateDish(UpdateDishRequestDto updateDishRequestDto);
    void toogleDishStatus(ToggleDishStatusRequestDto toggleDishStatusRequestDto);
}
