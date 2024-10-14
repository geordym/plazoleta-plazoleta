package com.plazoleta.plazoleta.infraestructure.configuration;

import com.plazoleta.plazoleta.domain.spi.IMessagerConnectionPort;
import com.plazoleta.plazoleta.infraestructure.out.feign.adapter.MessagerConnectionFeignAdapter;
import com.plazoleta.plazoleta.infraestructure.out.feign.client.IMessageConnectionFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MessageBeanConfiguration {

    private final IMessageConnectionFeignClient messageConnectionFeignClient;

    @Bean
    public IMessagerConnectionPort messagerConnectionPort(){
        return new MessagerConnectionFeignAdapter(messageConnectionFeignClient);
    }

}

