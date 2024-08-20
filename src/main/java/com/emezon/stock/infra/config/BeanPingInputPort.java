package com.emezon.stock.infra.config;

import com.emezon.stock.app.usecases.PingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.emezon.stock.domain.ports.input.IPingInputPort;

@Configuration
public class BeanPingInputPort {

    @Bean
    public IPingInputPort pingInputPort() {
        return new PingUseCase();
    }

}
