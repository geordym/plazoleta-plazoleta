package com.plazoleta.plazoleta.infraestructure.out.jpa.repository;

import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {


    @Query("SELECT d FROM DishEntity d WHERE d.restaurant.id = :restaurantId")
    Page<DishEntity> findAllByRestaurantId(@Param("restaurantId") Long restaurantId, Pageable pageable);

    @Query("SELECT d FROM DishEntity d WHERE d.restaurant.id = :restaurantId AND d.category = :category")
    Page<DishEntity> findAllByRestaurantIdAndCategory(@Param("category") String category, @Param("restaurantId") Long restaurantId, Pageable pageable);

    @Query("SELECT d FROM DishEntity d WHERE d.restaurant.id = :restaurantId AND d.id IN :dishIds")
    List<DishEntity> findByRestaurantIdAndDishIds(@Param("restaurantId") Long restaurantId, @Param("dishIds") List<Long> dishIds);

    boolean existsByName(String name);
}
