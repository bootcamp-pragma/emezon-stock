package com.emezon.stock.infra.config;

import com.emezon.stock.domain.usecases.PingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.emezon.stock.domain.ports.input.IPingInputPort;

@Configuration
public class PingInputPortConfig {

    @Bean
    public IPingInputPort pingInputPort() {
        return new PingUseCase();
    }

}
