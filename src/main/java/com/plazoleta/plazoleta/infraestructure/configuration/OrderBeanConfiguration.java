package com.plazoleta.plazoleta.infraestructure.configuration;


import com.plazoleta.plazoleta.application.handler.IOrderHandler;
import com.plazoleta.plazoleta.application.handler.impl.OrderHandlerImpl;
import com.plazoleta.plazoleta.application.mapper.IOrderRequestMapper;
import com.plazoleta.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.plazoleta.domain.spi.IDishPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IUserAuthenticationPort;
import com.plazoleta.plazoleta.domain.usecase.OrderUseCase;
import com.plazoleta.plazoleta.domain.usecase.validator.OrderUseCaseValidator;
import com.plazoleta.plazoleta.infraestructure.out.jpa.adapter.OrderJpaAdapter;
import com.plazoleta.plazoleta.infraestructure.out.jpa.mapper.IOrderEntityMapper;
import com.plazoleta.plazoleta.infraestructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OrderBeanConfiguration {

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IOrderRequestMapper orderRequestMapper;

    private final IUserAuthenticationPort userAuthenticationPort;

    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderUseCaseValidator(), orderPersistencePort(), userAuthenticationPort);
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderJpaAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public OrderUseCaseValidator orderUseCaseValidator(){
        return new OrderUseCaseValidator(restaurantPersistencePort, dishPersistencePort, orderPersistencePort());
    }

    @Bean
    public IOrderHandler orderHandler(){
        return new OrderHandlerImpl(orderServicePort(), orderRequestMapper);
    }


}
