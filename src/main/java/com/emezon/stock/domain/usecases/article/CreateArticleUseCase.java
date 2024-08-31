package com.emezon.stock.domain.usecases.article;

import com.emezon.stock.domain.constants.ArticleConstraints;
import com.emezon.stock.domain.exceptions.article.ArticleCategoriesNumberInvalidException;
import com.emezon.stock.domain.exceptions.article.ArticleDuplicateCategoriesException;
import com.emezon.stock.domain.exceptions.article.ArticlePriceMinValueException;
import com.emezon.stock.domain.exceptions.article.ArticleStockMinValueException;
import com.emezon.stock.domain.exceptions.brand.BrandNotFoundByIdException;
import com.emezon.stock.domain.exceptions.category.CategoryNotFoundByIdException;
import com.emezon.stock.domain.models.Article;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.inbound.article.ICreateArticleInPort;
import com.emezon.stock.domain.ports.outbound.IArticleRepositoryOutPort;
import com.emezon.stock.domain.usecases.brand.RetrieveBrandUseCase;
import com.emezon.stock.domain.usecases.category.RetrieveCategoryUseCase;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CreateArticleUseCase implements ICreateArticleInPort {

    private final IArticleRepositoryOutPort articleRepositoryOutPort;
    private final RetrieveCategoryUseCase retrieveCategoryUseCase;
    private final RetrieveBrandUseCase retrieveBrandUseCase;

    public CreateArticleUseCase(IArticleRepositoryOutPort articleRepositoryOutPort,
                                RetrieveCategoryUseCase retrieveCategoryUseCase,
                                RetrieveBrandUseCase retrieveBrandUseCase) {
        this.articleRepositoryOutPort = articleRepositoryOutPort;
        this.retrieveCategoryUseCase = retrieveCategoryUseCase;
        this.retrieveBrandUseCase = retrieveBrandUseCase;
    }

    @Override
    public Article createArticle(Article article) {
        Article processedArticle = processAndValidateArticle(article);
        Optional<Brand> brandById = retrieveBrandUseCase.getBrandById(processedArticle.getBrand().getId());
        if (brandById.isEmpty()) {
            throw new BrandNotFoundByIdException(processedArticle.getBrand().getId());
        }
        List<Category> categories = processedArticle.getCategories();
        for (Category category : categories) {
            Optional<Category> categoryById = retrieveCategoryUseCase.getCategoryById(category.getId());
            if (categoryById.isEmpty()) {
                throw new CategoryNotFoundByIdException(category.getId());
            }
        }
        return articleRepositoryOutPort.save(processedArticle);
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
