package com.emezon.stock.infra.config;

import com.emezon.stock.app.handlers.ICategoryHandler;
import com.emezon.stock.app.services.CategoryService;
import com.emezon.stock.domain.api.category.IPersistCategoryInPort;
import com.emezon.stock.domain.api.category.IRetrieveCategoryInPort;
import com.emezon.stock.domain.usecases.category.PersistCategoryUseCase;
import com.emezon.stock.domain.usecases.category.RetrieveCategoryUseCase;
import com.emezon.stock.domain.spi.ICategoryRepositoryOutPort;
import com.emezon.stock.infra.outbound.mysql.jpa.adapters.MySQLJPACategoryAdapter;
import com.emezon.stock.infra.outbound.mysql.jpa.repositories.IMySQLJPACategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CategoryConfig {

    private final IMySQLJPACategoryRepository mySQLJPACategoryRepository;

    @Bean
    public ICategoryRepositoryOutPort categoryRepositoryOutPort() {
        return new MySQLJPACategoryAdapter(mySQLJPACategoryRepository);
    }

    @Bean
    public IPersistCategoryInPort persistCategoryInPort() {
        return new PersistCategoryUseCase(categoryRepositoryOutPort());
    }

    @Bean
    public IRetrieveCategoryInPort retrieveCategoryInPort() {
        return new RetrieveCategoryUseCase(categoryRepositoryOutPort());
    }

    @Bean
    public ICategoryHandler categoryHandler() {
        return new CategoryService(persistCategoryInPort(), retrieveCategoryInPort());
    }

}
