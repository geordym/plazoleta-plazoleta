package com.plazoleta.plazoleta.application.handler;

import com.plazoleta.plazoleta.application.dto.request.CreateDishRequestDto;
import com.plazoleta.plazoleta.application.dto.request.ToggleDishStatusRequestDto;
import com.plazoleta.plazoleta.application.dto.request.UpdateDishRequestDto;
import com.plazoleta.plazoleta.application.dto.response.DishResponseDto;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.util.DishFilter;

public interface IDishHandler {

    void createDish(CreateDishRequestDto createDishRequestDto);
    void updateDish(UpdateDishRequestDto updateDishRequestDto);
    void toogleDishStatus(ToggleDishStatusRequestDto toggleDishStatusRequestDto);
    PaginationCustom<DishResponseDto> listDishesOfResturant(String category, Long restaurantId, int page, int size, String sortBy, boolean ascending);
}
