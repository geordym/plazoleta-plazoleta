package com.plazoleta.plazoleta.infraestructure.configuration;


import com.plazoleta.plazoleta.domain.spi.ITraceabilityConnectionPort;
import com.plazoleta.plazoleta.infraestructure.out.feign.adapter.TraceabiltyConnectionFeignAdapter;
import com.plazoleta.plazoleta.infraestructure.out.feign.client.ITraceabilityConnectionFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TraceabilityBeanConfiguraton {

    private final ITraceabilityConnectionFeignClient iTraceabilityConnectionFeignClient;

    @Bean
    public ITraceabilityConnectionPort traceabilityConnectionPort(){
        return new TraceabiltyConnectionFeignAdapter(iTraceabilityConnectionFeignClient);
    }


}
