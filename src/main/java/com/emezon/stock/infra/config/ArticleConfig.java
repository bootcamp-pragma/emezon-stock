package com.emezon.stock.infra.config;

import com.emezon.stock.app.handlers.IArticleHandler;
import com.emezon.stock.app.services.ArticleService;
import com.emezon.stock.domain.api.article.IPersistArticleInPort;
import com.emezon.stock.domain.api.article.IRetrieveArticleInPort;
import com.emezon.stock.domain.api.brand.IRetrieveBrandInPort;
import com.emezon.stock.domain.api.category.IRetrieveCategoryInPort;
import com.emezon.stock.domain.spi.IArticleRepositoryOutPort;
import com.emezon.stock.domain.usecases.article.PersistArticleUseCase;
import com.emezon.stock.domain.usecases.article.RetrieveArticleUseCase;
import com.emezon.stock.infra.outbound.mysql.jpa.adapters.MySQLJPAArticleAdapter;
import com.emezon.stock.infra.outbound.mysql.jpa.repositories.IMySQLJPAArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ArticleConfig {

    private final IMySQLJPAArticleRepository mySQLJPAArticleRepository;
    private final IRetrieveCategoryInPort retrieveCategoryInPort;
    private final IRetrieveBrandInPort retrieveBrandInPort;

    @Bean
    public IArticleRepositoryOutPort articleRepositoryOutPort() {
        return new MySQLJPAArticleAdapter(mySQLJPAArticleRepository);
    }

    @Bean
    public IPersistArticleInPort persistArticleInPort() {
        return new PersistArticleUseCase(articleRepositoryOutPort(), retrieveCategoryInPort, retrieveBrandInPort);
    }

    @Bean
    public IRetrieveArticleInPort retrieveArticleInPort() {
        return new RetrieveArticleUseCase(articleRepositoryOutPort());
    }

    @Bean
    public IArticleHandler articleHandler() {
        return new ArticleService(persistArticleInPort(), retrieveArticleInPort());
    }


}
