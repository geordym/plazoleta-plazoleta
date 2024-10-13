package com.plazoleta.plazoleta.infraestructure.out.jpa.repository;

import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT COUNT(o) FROM OrderEntity o WHERE o.customerId = :customerId AND o.status IN :statuses")
    Long countActiveOrdersByCustomerId(@Param("customerId") Long customerId, @Param("statuses") List<OrderStatus> statuses);

    @Query("SELECT o FROM OrderEntity o WHERE o.restaurant.id = :restaurantId")
    Page<OrderEntity> findOrdersByRestaurantId(@Param("restaurantId") Long restaurantId, Pageable pageable);

    @Query("SELECT o FROM OrderEntity o WHERE o.restaurant.id = :restaurantId AND o.status = :status")
    Page<OrderEntity> findOrdersByRestaurantIdAndStatus(@Param("restaurantId") Long restaurantId, @Param("status") OrderStatus status, Pageable pageable);

}
