package com.emezon.stock.infra.config;

import com.emezon.stock.app.handlers.IBrandHandler;
import com.emezon.stock.app.services.BrandService;
import com.emezon.stock.domain.api.brand.IPersistBrandInPort;
import com.emezon.stock.domain.api.brand.IRetrieveBrandInPort;
import com.emezon.stock.domain.spi.IBrandRepositoryOutPort;
import com.emezon.stock.domain.usecases.brand.PersistBrandUseCase;
import com.emezon.stock.domain.usecases.brand.RetrieveBrandUseCase;
import com.emezon.stock.infra.output.mysql.jpa.adapters.MySQLJPABrandAdapter;
import com.emezon.stock.infra.output.mysql.jpa.repositories.IMySQLJPABrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BrandConfig {
    private final IMySQLJPABrandRepository mySQLJPABrandRepository;

    @Bean
    public IBrandRepositoryOutPort brandRepositoryOutPort() {
        return new MySQLJPABrandAdapter(mySQLJPABrandRepository);
    }

    @Bean
    public IPersistBrandInPort persistBrandInPort() {
        return new PersistBrandUseCase(brandRepositoryOutPort());
    }

    @Bean
    public IRetrieveBrandInPort retrieveBrandInPort() {
        return new RetrieveBrandUseCase(brandRepositoryOutPort());
    }

    @Bean
    public IBrandHandler brandHandler() {
        return new BrandService(persistBrandInPort(), retrieveBrandInPort());
    }

}
