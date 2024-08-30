package com.emezon.stock.domain.usecases.article;

import com.emezon.stock.domain.constants.ArticleConstraints;
import com.emezon.stock.domain.models.Article;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.inbound.article.ICreateArticleInPort;
import com.emezon.stock.domain.ports.inbound.category.IRetrieveCategoryInPort;

import java.util.List;
import java.util.Optional;

public class CreateArticleUseCase implements ICreateArticleInPort {

    private final ICreateArticleInPort createArticleInPort;
    private final IRetrieveCategoryInPort retrieveCategoryInPort;

    public CreateArticleUseCase(ICreateArticleInPort createArticleInPort, IRetrieveCategoryInPort retrieveCategoryInPort) {
        this.createArticleInPort = createArticleInPort;
        this.retrieveCategoryInPort = retrieveCategoryInPort;
    }

    @Override
    public Article createArticle(Article article) {
        Article processedArticle = processAndValidateArticle(article);
        List<Category> categories = processedArticle.getCategories();
        for (Category category : categories) {
            Optional<Category> categoryById = retrieveCategoryInPort.getCategoryById(category.getId());
            if (categoryById.isEmpty()) {
                throw new RuntimeException("Category not found");
            }
        }
        return createArticleInPort.createArticle(processedArticle);
    }

    private Article processAndValidateArticle(Article article) {
//        if (article.getPrice() < ArticleConstraints.PRICE_MIN_VALUE) {
////            throw new ArticlePriceMinValueException(ArticleConstraints.PRICE_MIN_VALUE);
//        }
        return article;
    }

}
