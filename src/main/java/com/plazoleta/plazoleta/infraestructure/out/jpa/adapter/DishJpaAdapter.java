package com.plazoleta.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.spi.IDishPersistencePort;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.DishEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.mapper.IDishEntityMapper;
import com.plazoleta.plazoleta.infraestructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;

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

}
