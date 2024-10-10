package com.plazoleta.plazoleta.infraestructure.configuration;


import com.plazoleta.plazoleta.application.handler.IPlazoletaHandler;
import com.plazoleta.plazoleta.application.handler.impl.PlazoletaHandlerImpl;
import com.plazoleta.plazoleta.application.mapper.IRestaurantRequestMapper;
import com.plazoleta.plazoleta.application.mapper.IRestaurantResponseMapper;
import com.plazoleta.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IUserConnectionPort;
import com.plazoleta.plazoleta.domain.usecase.RestaurantUseCase;
import com.plazoleta.plazoleta.infraestructure.out.feign.adapter.UserConnectionFeignAdapter;
import com.plazoleta.plazoleta.infraestructure.out.feign.client.IUserConnectionFeignClient;
import com.plazoleta.plazoleta.infraestructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.plazoleta.plazoleta.infraestructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.plazoleta.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    private final IUserConnectionFeignClient userConnectionFeignClient;


    @Bean
    public IPlazoletaHandler plazoletaHandler(){
        return new PlazoletaHandlerImpl(restaurantServicePort(), restaurantRequestMapper);
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IUserConnectionPort userConnectionPort(){
        return new UserConnectionFeignAdapter(userConnectionFeignClient);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(){
       return new RestaurantUseCase(restaurantPersistencePort(), userConnectionPort());
    }


}
