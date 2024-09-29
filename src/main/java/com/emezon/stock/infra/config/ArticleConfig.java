package com.emezon.stock.infra.config;

import com.emezon.stock.app.services.ArticleService;
import com.emezon.stock.domain.ports.outbound.IArticleRepositoryOutPort;
import com.emezon.stock.domain.ports.outbound.IBrandRepositoryOutPort;
import com.emezon.stock.domain.ports.outbound.ICategoryRepositoryOutPort;
import com.emezon.stock.domain.usecases.article.CreateArticleUseCase;
import com.emezon.stock.domain.usecases.article.RetrieveArticleUseCase;
import com.emezon.stock.domain.usecases.brand.RetrieveBrandUseCase;
import com.emezon.stock.domain.usecases.category.RetrieveCategoryUseCase;
import com.emezon.stock.infra.output.mysql.jpa.adapters.MySQLJPAArticleAdapter;
import com.emezon.stock.infra.output.mysql.jpa.repositories.IMySQLJPAArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleConfig {

    @Bean
    public IArticleRepositoryOutPort articleRepositoryOutPort(IMySQLJPAArticleRepository mySQLJPAArticleRepository) {
        return new MySQLJPAArticleAdapter(mySQLJPAArticleRepository);
    }

    @Bean
    public ArticleService articleService(IArticleRepositoryOutPort articleRepositoryOutPort,
                                                          ICategoryRepositoryOutPort categoryRepositoryOutPort,
                                                          IBrandRepositoryOutPort brandRepositoryOutPort) {
        return new ArticleService(
                new CreateArticleUseCase(articleRepositoryOutPort,
                        new RetrieveCategoryUseCase(categoryRepositoryOutPort),
                        new RetrieveBrandUseCase(brandRepositoryOutPort)),
                new RetrieveArticleUseCase(articleRepositoryOutPort)
        );
    }

}
