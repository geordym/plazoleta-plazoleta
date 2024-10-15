package com.plazoleta.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.plazoleta.domain.enums.RestaurantSortBy;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;
import com.plazoleta.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.DishEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.plazoleta.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {


    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;


    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
        return restaurantEntityMapper.toModel(restaurantEntity);
    }

    @Override
    public Optional<Restaurant> findRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .map(restaurantEntityMapper::toModel);
    }

    @Override
    public PaginationCustom<Restaurant> findAllRestaurant(PaginationParams<RestaurantSortBy> paginationParams) {
        PageRequest pageRequest = PageRequest.of(
                paginationParams.getPage(),
                paginationParams.getSize(),
                paginationParams.isAscending() ? Sort.by(paginationParams.getSortBy().entityAttribute()).ascending() : Sort.by(paginationParams.getSortBy().entityAttribute()).descending()
        );

        Page<RestaurantEntity> restaurantPage = restaurantRepository.findAll(pageRequest);
        List<Restaurant> restaurantList = restaurantPage.getContent()
                .stream()
                .map(restaurantEntityMapper::toModel)
                .toList();

        return new PaginationCustom<>(
                restaurantList,
                restaurantPage.getNumber(),
                restaurantPage.getSize(),
                restaurantPage.getTotalElements(),
                restaurantPage.getTotalPages(),
                restaurantPage.isLast()
        );
    }

    @Override
    public boolean existsRestaurantById(Long restaurantId) {
        return restaurantRepository.existsById(restaurantId);
    }

    @Override
    public Optional<Restaurant> findRestaurantByOwnerId(Long ownerId) {
        return restaurantRepository.findByOwnerId(ownerId)
                .map(restaurantEntityMapper::toModel);
    }

    @Override
    public boolean existsRestaurantByName(String name) {
        return restaurantRepository.existsByName(name);
    }

    @Override
    public boolean existsRestaurantByNit(Long nit) {
        return restaurantRepository.existsByNit(nit);
    }

    @Override
    public boolean existsRestaurantByPhone(String phoneNumber) {
        return restaurantRepository.existsByPhone(phoneNumber);
    }




}
