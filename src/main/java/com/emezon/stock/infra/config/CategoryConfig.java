package com.emezon.stock.infra.config;

import com.emezon.stock.app.services.CategoryService;
import com.emezon.stock.domain.usecases.category.CreateCategoryUseCase;
import com.emezon.stock.domain.usecases.category.RetrieveCategoryUseCase;
import com.emezon.stock.domain.spi.ICategoryRepositoryOutPort;
import com.emezon.stock.infra.output.mysql.jpa.adapters.MySQLJPACategoryAdapter;
import com.emezon.stock.infra.output.mysql.jpa.repositories.IMySQLJPACategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfig {

    @Bean
    public ICategoryRepositoryOutPort categoryRepositoryOutPort(IMySQLJPACategoryRepository mySQLJPACategoryRepository) {
        return new MySQLJPACategoryAdapter(mySQLJPACategoryRepository);
    }

    @Bean
    public CategoryService categoryService(ICategoryRepositoryOutPort categoryRepositoryOutPort) {
        return new CategoryService(
                new CreateCategoryUseCase(categoryRepositoryOutPort),
                new RetrieveCategoryUseCase(categoryRepositoryOutPort)
        );
    }

}
