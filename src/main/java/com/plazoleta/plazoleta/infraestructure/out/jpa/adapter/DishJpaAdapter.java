package com.plazoleta.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.plazoleta.domain.enums.DishEnumSortBy;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;
import com.plazoleta.plazoleta.domain.spi.IDishPersistencePort;
import com.plazoleta.plazoleta.domain.util.DishFilter;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.DishEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.mapper.IDishEntityMapper;
import com.plazoleta.plazoleta.infraestructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;


    @Override
    public Dish saveDish(Dish dish) {
        DishEntity dishEntity = dishRepository.save(dishEntityMapper.toEntity(dish));
        return dishEntityMapper.toModel(dishEntity);
    }


    @Override
    public Optional<Dish> findDishById(Long dishId) {
        return dishRepository.findById(dishId).map(dishEntityMapper::toModel);
    }

    @Override
    public Dish updateDish(Dish dish) {
        DishEntity dishEntity = dishRepository.save(dishEntityMapper.toEntity(dish));
        return dishEntityMapper.toModel(dishEntity);
    }

    @Override
    public PaginationCustom<Dish> listDishByRestaurantId(DishFilter dishFilter, Long restaurantId, PaginationParams<DishEnumSortBy> paginationParams) {
        String categoryFilter = dishFilter.getCategory();

        PageRequest pageRequest = PageRequest.of(
                paginationParams.getPage(),
                paginationParams.getSize(),
                paginationParams.isAscending() ? Sort.by(paginationParams.getSortBy().entityAttribute()).ascending() : Sort.by(paginationParams.getSortBy().entityAttribute()).descending()
        );

        Page<DishEntity> dishEntityPage = (categoryFilter == null || categoryFilter.isBlank())
                ? dishRepository.findAllByRestaurantId(restaurantId, pageRequest)
                : dishRepository.findAllByRestaurantIdAndCategory(categoryFilter, restaurantId, pageRequest);


        List<Dish> dishList = convertToDishList(dishEntityPage);

        return new PaginationCustom<>(
                dishList,
                dishEntityPage.getNumber(),
                dishEntityPage.getSize(),
                dishEntityPage.getTotalElements(),
                dishEntityPage.getTotalPages(),
                dishEntityPage.isLast()
        );

    }

    private List<Dish> convertToDishList(Page<DishEntity> dishEntityPage) {
        return dishEntityPage.getContent().stream()
                .map(dishEntityMapper::toModel)
                .toList();
    }

}
