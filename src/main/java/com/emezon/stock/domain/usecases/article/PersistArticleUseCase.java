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

import java.time.LocalDateTime;
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
    public Article addSupply(String id, int quantity, LocalDateTime restockDate) {
        Optional<Article> articleById = articleRepositoryOutPort.findById(id);
        if (articleById.isEmpty()) {
            throw new ArticleNotFoundByIdException(id);
        }
        Article article = articleById.get();
        article.setStock(article.getStock() + quantity);
        article.setRestockDate(restockDate);
        return articleRepositoryOutPort.save(article);
    }

    @Override
    public Article addSupply(String id, Map<String, Object> payload) {
        if (!isValidMapStructure(payload)) {
            throw new IllegalArgumentException("Invalid payload structure");
        }
        int exp = (int) payload.get("exp");
        Date date = new Date(exp * 1000L);
        if (date.before(new Date())) {
            throw new IllegalArgumentException("Invalid expiration date");
        }
        String restockDate = (String) payload.get("restockDate");
        LocalDateTime restockDateLocal = null;
        if (restockDate != null) {
            try {
                restockDateLocal = LocalDateTime.parse(restockDate).withNano(0).withSecond(0).withMinute(0);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid restock date");
            }
        }
        int quantity = (int) payload.get("quantity");
        if (quantity < 0) {
            throw new IllegalArgumentException("Invalid quantity");
        }
        return addSupply(id, quantity, restockDateLocal);
    }

    @Override
    public Article updateRestockDate(String id, LocalDateTime restockDate) {
        Optional<Article> articleById = articleRepositoryOutPort.findById(id);
        if (articleById.isEmpty()) {
            throw new ArticleNotFoundByIdException(id);
        }
        Article article = articleById.get();
        article.setRestockDate(restockDate);
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

    public boolean isValidMapStructure(Map<String, Object> payload) {
        Map<String, Class<?>> requiredFields =Map.of(
                "quantity", Integer.class,
                "exp", Integer.class
        );
        for (Map.Entry<String, Class<?>> entry : requiredFields.entrySet()) {
            Class<?> type = entry.getValue();
            Object value = payload.get(entry.getKey());
            if (!payload.containsKey(entry.getKey()) || !type.isInstance(value)) {
                return false;
            }
        }
        Map<String, Class<?>> optionalFields = Map.of(
                "restockDate", String.class
        );
        for (Map.Entry<String, Class<?>> entry : optionalFields.entrySet()) {
            Class<?> type = entry.getValue();
            Object value = payload.get(entry.getKey());
            if (value != null && !type.isInstance(value)) {
                return false;
            }
        }
        return true;
    }

}
