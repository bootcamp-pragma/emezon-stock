package com.emezon.stock.domain.usecases.article;

import com.emezon.stock.domain.api.brand.IRetrieveBrandInPort;
import com.emezon.stock.domain.api.category.IRetrieveCategoryInPort;
import com.emezon.stock.domain.constants.ArticleConstraints;
import com.emezon.stock.domain.exceptions.article.*;
import com.emezon.stock.domain.exceptions.brand.BrandNotFoundByIdException;
import com.emezon.stock.domain.exceptions.category.CategoryNotFoundByIdException;
import com.emezon.stock.domain.models.Article;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.api.article.IPersistArticleInPort;
import com.emezon.stock.domain.spi.IArticleRepositoryOutPort;

import java.util.*;

public class PersistArticleUseCase implements IPersistArticleInPort {

    private final IArticleRepositoryOutPort articleRepositoryOutPort;
    private final IRetrieveCategoryInPort retrieveCategoryInPort;
    private final IRetrieveBrandInPort retrieveBrandInPort;

    public PersistArticleUseCase(IArticleRepositoryOutPort articleRepositoryOutPort,
                                 IRetrieveCategoryInPort retrieveCategoryInPort,
                                 IRetrieveBrandInPort retrieveBrandInPort) {
        this.articleRepositoryOutPort = articleRepositoryOutPort;
        this.retrieveCategoryInPort = retrieveCategoryInPort;
        this.retrieveBrandInPort = retrieveBrandInPort;
    }

    @Override
    public Article createArticle(Article article) {
        Article processedArticle = processAndValidateArticle(article);
        Optional<Brand> brandById = retrieveBrandInPort.getBrandById(processedArticle.getBrand().getId());
        if (brandById.isEmpty()) {
            throw new BrandNotFoundByIdException(processedArticle.getBrand().getId());
        }
        processedArticle.setBrand(brandById.get());
        List<Category> categories = processedArticle.getCategories();
        processedArticle.setCategories(new ArrayList<>());
        for (Category category : categories) {
            Optional<Category> categoryById = retrieveCategoryInPort.getCategoryById(category.getId());
            if (categoryById.isEmpty()) {
                throw new CategoryNotFoundByIdException(category.getId());
            }
            processedArticle.getCategories().add(categoryById.get());
        }
        return articleRepositoryOutPort.save(processedArticle);
    }

    @Override
    public Article addSupply(String id, int quantity) {
        Optional<Article> articleById = articleRepositoryOutPort.findById(id);
        if (articleById.isEmpty()) {
            throw new ArticleNotFoundByIdException(id);
        }
        Article article = articleById.get();
        article.setStock(article.getStock() + quantity);
        return articleRepositoryOutPort.save(article);
    }

    private Article processAndValidateArticle(Article article) {
        if (article.getPrice() < ArticleConstraints.PRICE_MIN_VALUE) {
            throw new ArticlePriceMinValueException(ArticleConstraints.PRICE_MIN_VALUE);
        }
        if (article.getStock() < ArticleConstraints.STOCK_MIN_VALUE) {
            throw new ArticleStockMinValueException(ArticleConstraints.STOCK_MIN_VALUE);
        }
        Set<Category> categories = new HashSet<>(article.getCategories());
        if (categories.size() != article.getCategories().size()) {
            throw new ArticleDuplicateCategoriesException();
        }
        if (article.getCategories().size() < ArticleConstraints.MIN_NUMBER_OF_CATEGORIES
                || article.getCategories().size() > ArticleConstraints.MAX_NUMBER_OF_CATEGORIES) {
            throw new ArticleCategoriesNumberInvalidException(ArticleConstraints.MIN_NUMBER_OF_CATEGORIES,
                    ArticleConstraints.MAX_NUMBER_OF_CATEGORIES);
        }
        return article;
    }

}
