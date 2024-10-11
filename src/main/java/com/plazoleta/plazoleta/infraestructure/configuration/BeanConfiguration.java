package com.plazoleta.plazoleta.infraestructure.configuration;


import com.plazoleta.plazoleta.application.handler.IDishHandler;
import com.plazoleta.plazoleta.application.handler.IPlazoletaHandler;
import com.plazoleta.plazoleta.application.handler.impl.DishHandlerImpl;
import com.plazoleta.plazoleta.application.handler.impl.PlazoletaHandlerImpl;
import com.plazoleta.plazoleta.application.mapper.IDishRequestMapper;
import com.plazoleta.plazoleta.application.mapper.IDishResponseMapper;
import com.plazoleta.plazoleta.application.mapper.IRestaurantRequestMapper;
import com.plazoleta.plazoleta.application.mapper.IRestaurantResponseMapper;
import com.plazoleta.plazoleta.domain.api.IDishServicePort;
import com.plazoleta.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.plazoleta.domain.spi.IDishPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IUserAuthenticationPort;
import com.plazoleta.plazoleta.domain.spi.IUserConnectionPort;
import com.plazoleta.plazoleta.domain.spi.security.ITokenProviderPort;
import com.plazoleta.plazoleta.domain.usecase.DishUseCase;
import com.plazoleta.plazoleta.domain.usecase.RestaurantUseCase;
import com.plazoleta.plazoleta.infraestructure.adapter.security.JwtIOTokenAdapter;
import com.plazoleta.plazoleta.infraestructure.out.feign.adapter.UserConnectionFeignAdapter;
import com.plazoleta.plazoleta.infraestructure.out.feign.client.IUserConnectionFeignClient;
import com.plazoleta.plazoleta.infraestructure.out.jpa.adapter.DishJpaAdapter;
import com.plazoleta.plazoleta.infraestructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.plazoleta.plazoleta.infraestructure.out.jpa.mapper.IDishEntityMapper;
import com.plazoleta.plazoleta.infraestructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.plazoleta.infraestructure.out.jpa.repository.IDishRepository;
import com.plazoleta.plazoleta.infraestructure.out.jpa.repository.IRestaurantRepository;
import com.plazoleta.plazoleta.infraestructure.out.auth.UserAuthenticationAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    private final IUserConnectionFeignClient userConnectionFeignClient;

    private final IDishRepository dishRepository;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;
    private final IDishEntityMapper dishEntityMapper;

    @Bean
    public ITokenProviderPort tokenProviderPort(){
        return new JwtIOTokenAdapter();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IUserAuthenticationPort userAuthenticationPort(){
        return new UserAuthenticationAdapter();
    }

    @Bean
    public IDishPersistencePort dishPersistencePort(){
        return new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort(){
        return new DishUseCase(dishPersistencePort(), userAuthenticationPort(), restaurantPersistencePort());
    }

    @Bean
    public IDishHandler dishHandler(){
        return new DishHandlerImpl(dishServicePort(), dishRequestMapper);
    }

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
