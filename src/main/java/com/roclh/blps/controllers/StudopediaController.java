package com.roclh.blps.controllers;

import com.roclh.blps.Exceptions.ArticleNotFoundException;
import com.roclh.blps.Exceptions.DataValidationException;
import com.roclh.blps.entities.StudopediaArticle;
import com.roclh.blps.service.StudopediaService;
import com.roclh.blps.utils.ValidationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class StudopediaController {

    private static final Logger log = LogManager.getLogger(StudopediaController.class);
    private static final String SPECIAL_CHARACTERS_MESSAGE = "There is special characters in request";
    private static final String EMPTY = "The request is empty";
    private static final String WRONG_PAGE_NUMBER = "Wrong page number";
    private final StudopediaService service;

    @Autowired
    public StudopediaController(StudopediaService service) {
        log.info("Initializing Studopedia Controller");
        this.service = service;
    }

    @PostMapping("/article")
    @ApiOperation(value = "Get article by name", notes = "Throws exception if Article doesn't exist")
    public StudopediaArticle getArticle(@RequestParam(name="name") String articleName) throws ArticleNotFoundException {
        log.info("Received request to get article with name {}", articleName);
        ValidationUtils.validate(articleName, String::isEmpty, EMPTY);
        ValidationUtils.validate(articleName, ValidationUtils::containsSpecialCharacters, SPECIAL_CHARACTERS_MESSAGE);
        return service.getArticleByName(articleName);
    }

    @PostMapping("/all-articles")
    public List<StudopediaArticle> getAllArticle(@RequestParam(name="page") int page){
        log.info("Received request to get all the articles");
        ValidationUtils.validate(page, (val) -> val >= 0, WRONG_PAGE_NUMBER);
        return service.getArticlesAsList(page);
    }

    @PostMapping("/article/search")
    public List<StudopediaArticle> searchArticle(@RequestParam(name = "search") String search, @RequestParam(name = "page") int page) throws DataValidationException {
        log.info("Received a search for " + search + " article request");
        ValidationUtils.validate(search, String::isEmpty, EMPTY);
        ValidationUtils.validate(search, ValidationUtils::containsSpecialCharacters, SPECIAL_CHARACTERS_MESSAGE);
        ValidationUtils.validate(page, (val) -> val >= 0, WRONG_PAGE_NUMBER);
        return service.getArticlesAsPage(search, page);
    }

    @PostMapping("/article/suggest")
    public List<StudopediaArticle> suggestArticle(@RequestParam(name = "search") String search) throws DataValidationException {
        log.info("Suggestions!!!");
        ValidationUtils.validate(search, String::isEmpty, EMPTY);
        ValidationUtils.validate(search, ValidationUtils::containsSpecialCharacters, SPECIAL_CHARACTERS_MESSAGE);
        return service.getArticleSuggestionBySubStr(search);
    }

    @GetMapping("/article/random")
    public StudopediaArticle randomArticle() throws ArticleNotFoundException {
        log.info("Received a request for random article");
        return service.getRandomArticle();
    }

    @PostMapping("/article/category")
    public List<StudopediaArticle> getArticlesByCategory(
            @RequestParam(name = "category") String categoryName, @RequestParam(name = "page") int page) throws ArticleNotFoundException, DataValidationException {
        log.info("Received a request for articles in category: " + categoryName);
        ValidationUtils.validate(categoryName, ValidationUtils::containsSpecialCharacters, SPECIAL_CHARACTERS_MESSAGE);
        ValidationUtils.validate(categoryName, String::isEmpty, EMPTY);
        ValidationUtils.validate(page, (val) -> val >= 0, WRONG_PAGE_NUMBER);
        return service.getArticleByCategory(categoryName, page);
    }
}




