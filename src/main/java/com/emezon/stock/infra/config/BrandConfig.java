package com.emezon.stock.infra.config;

import com.emezon.stock.app.services.BrandService;
import com.emezon.stock.domain.ports.outbound.IBrandRepositoryOutPort;
import com.emezon.stock.domain.usecases.brand.CreateBrandUseCase;
import com.emezon.stock.domain.usecases.brand.RetrieveBrandUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrandConfig {

    @Bean
    public BrandService brandService(IBrandRepositoryOutPort brandRepositoryOutPort) {
        return new BrandService(
                new CreateBrandUseCase(brandRepositoryOutPort),
                new RetrieveBrandUseCase(brandRepositoryOutPort)
        );
    }

}
