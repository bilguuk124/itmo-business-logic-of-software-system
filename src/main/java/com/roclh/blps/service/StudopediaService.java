package com.roclh.blps.service;

import com.roclh.blps.Exceptions.ArticleNotFoundException;
import com.roclh.blps.Exceptions.DataValidationException;
import com.roclh.blps.database.CategoryDatabase;
import com.roclh.blps.database.StudopediaDatabase;
import com.roclh.blps.entities.Account;
import com.roclh.blps.entities.Category;
import com.roclh.blps.entities.StudopediaArticle;
import com.roclh.blps.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class StudopediaService {
    private final static String ARTICLE_EXISTS = "Article already exists";

    private final int DEFAULT_SIZE = 5;

    private final StudopediaDatabase articleRepository;
    private final CategoryDatabase categoryRepository;
    private final Principal principal;


    public List<StudopediaArticle> getArticlesAsList(int page) {
        Pageable pageWithFiveElements = PageRequest.of(page, DEFAULT_SIZE);
        return articleRepository.findAll(pageWithFiveElements).getContent();
    }

    public List<StudopediaArticle> getArticlesAsListWithPageSize(int page, int page_size) {
        Pageable pages = PageRequest.of(page, page_size);
        return articleRepository.findAll(pages).getContent();
    }

    public List<StudopediaArticle> getArticlesAsPage(String search, int page) {
        Pageable pageWithFiveElements = PageRequest.of(page, DEFAULT_SIZE);
        return articleRepository.findByNameContainsIgnoreCase(search, pageWithFiveElements);
    }

    public List<StudopediaArticle> getArticlesAsPageWithSize(String search, int page, int pageSize) {
        Pageable pages = PageRequest.of(page, pageSize);
        return articleRepository.findByNameContainsIgnoreCase(search, pages);
    }

    public StudopediaArticle getArticleByID(Long id) throws ArticleNotFoundException {
        Optional<StudopediaArticle> optional = articleRepository.findByIdEquals(id);
        if (optional.isEmpty()) throw new ArticleNotFoundException();
        return optional.get();
    }

    public StudopediaArticle getArticleByName(String name) throws ArticleNotFoundException {
        Optional<StudopediaArticle> optional = articleRepository.findByNameEqualsIgnoreCase(name);
        if (optional.isEmpty()) throw new ArticleNotFoundException();
        return optional.get();
    }

    public List<StudopediaArticle> getArticleSuggestionBySubStr(String subString) {
        int FIRST_ARTICLE = 0;
        Pageable pageOneWithFiveElements = PageRequest.of(FIRST_ARTICLE, DEFAULT_SIZE);
        return articleRepository.findByNameContainsIgnoreCase(subString, pageOneWithFiveElements);
    }

    public List<StudopediaArticle> getArticleByCategory(String category, int page) throws ArticleNotFoundException {
        Pageable pageWithFiveElements = PageRequest.of(page, DEFAULT_SIZE);
        Optional<Category> categoryOptional = categoryRepository.findByNameLikeIgnoreCase(category);
        if (categoryOptional.isEmpty()) throw new ArticleNotFoundException();
        return articleRepository.findByCategoryEquals(categoryOptional.get(), pageWithFiveElements);
    }

    public List<StudopediaArticle> getArticleByCategoryWithSize(String category, int page, int pageSize) throws ArticleNotFoundException {
        Pageable pages = PageRequest.of(page, pageSize);
        Optional<Category> categoryOptional = categoryRepository.findByNameLikeIgnoreCase(category);
        if (categoryOptional.isEmpty()) throw new ArticleNotFoundException();
        return articleRepository.findByCategoryEquals(categoryOptional.get(), pages);
    }

    public List<StudopediaArticle> getAllArticles() {
        return articleRepository.getAllArticles();
    }

    public long getArticleCount() {
        return articleRepository.count();
    }

    public StudopediaArticle getRandomArticle() throws ArticleNotFoundException {
        List<StudopediaArticle> allArticles = getAllArticles();
        Random random = new Random();
        if (allArticles.size() == 0) {
            throw new ArticleNotFoundException();
        }
        int randomIndex = random.nextInt(allArticles.size());
        return allArticles.get(randomIndex);
    }

    public void addArticle(String title, String content, String category) throws DataValidationException {
        ValidationUtils.validate(title, (val) -> articleRepository.findByNameEqualsIgnoreCase(val).isPresent(), ARTICLE_EXISTS);
        Category categoryObj = getOrCreateCategory(category);
        categoryRepository.save(categoryObj);
        articleRepository.save(new StudopediaArticle(
                title,
                content,
                categoryObj,
                ((Account) principal).getId()
        ));
    }

    private Category getOrCreateCategory(String category) {
        return categoryRepository.findByNameLikeIgnoreCase(category).orElse(new Category(category));
    }


}
