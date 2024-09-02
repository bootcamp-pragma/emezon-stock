package com.emezon.stock.infra.config;

import com.emezon.stock.app.services.ArticleService;
import com.emezon.stock.domain.ports.outbound.IArticleRepositoryOutPort;
import com.emezon.stock.domain.ports.outbound.IBrandRepositoryOutPort;
import com.emezon.stock.domain.ports.outbound.ICategoryRepositoryOutPort;
import com.emezon.stock.domain.usecases.article.CreateArticleUseCase;
import com.emezon.stock.domain.usecases.brand.RetrieveBrandUseCase;
import com.emezon.stock.domain.usecases.category.RetrieveCategoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleConfig {

    @Bean
    public ArticleService articleService(IArticleRepositoryOutPort articleRepositoryOutPort,
                                         ICategoryRepositoryOutPort categoryRepositoryOutPort,
                                         IBrandRepositoryOutPort brandRepositoryOutPort) {
        return new ArticleService(
                new CreateArticleUseCase(articleRepositoryOutPort,
                        new RetrieveCategoryUseCase(categoryRepositoryOutPort),
                        new RetrieveBrandUseCase(brandRepositoryOutPort))
        );
    }

}
