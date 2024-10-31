package com.emezon.stock.infra.config;

import com.emezon.stock.app.services.BrandService;
import com.emezon.stock.domain.spi.IBrandRepositoryOutPort;
import com.emezon.stock.domain.usecases.brand.CreateBrandUseCase;
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
    public BrandService brandService() {
        return new BrandService(
                new CreateBrandUseCase(brandRepositoryOutPort()),
                new RetrieveBrandUseCase(brandRepositoryOutPort())
        );
    }

}
