package com.plazoleta.plazoleta.infraestructure.configuration;


import com.plazoleta.plazoleta.application.handler.IRestaurantHandler;
import com.plazoleta.plazoleta.application.handler.impl.RestaurantHandlerImpl;
import com.plazoleta.plazoleta.application.mapper.IRestaurantResponseMapper;
import com.plazoleta.plazoleta.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RestaurantBeanConfiguration {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    @Bean
    public IRestaurantHandler restaurantHandler(){
        return new RestaurantHandlerImpl(restaurantServicePort, restaurantResponseMapper);
    }

}
